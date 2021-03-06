package td.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.io.Serializable;

/** Encapsulates a Tower Defense game's mechanics. */
@SuppressWarnings("serial")
public class TowerDefense implements Serializable {
    private int money;
    private int health;
    private Field field;
    private Wave wave;
    private transient List<Enemy> enemies;
    private List<Tower> towers;
    private int lastPurchasePrice;

    private final int towerPrice = 10;

    /**
     * Constructs a new TowerDefense.
     *
     * @param field        the field on which to play
     */
    public TowerDefense(Field field) {
        this.money = 40;
        this.health = 50;
        this.field = field;
        this.enemies = new ArrayList<>(100);
        this.towers = new ArrayList<>();
        this.lastPurchasePrice = 0;

        Enemy firstEnemyType = new Enemy(
                field.getSpawnPositionY(),
                field.getSpawnPositionX(),
                10, // health,
                40.); // speed
        this.wave = new Wave(firstEnemyType, 0);
    }

    public int getMoney() {
        return money;
    }

    /**
     * Get current wave number (nth).
     *
     * @return wave number
     */
    public int getWaveNumber() {
        return wave.getNumber();
    }

    /**
     * Check if current wave can be considered ended.
     *
     * @return true if no more enemies alive and wave's enemy spawning has ended, otherwise false
     */
    public boolean isWaveOver() {
        return enemies.isEmpty() && wave.isSpawningFinished();
    }

    /**
     * Checks if base health has reached 0.
     *
     * @return true if base health is 0 or under, otherwise false
     */
    public boolean isGameOver() {
        return health <= 0;
    }

    /**
     * Start next wave.
     */
    public void nextWave() {
        Enemy oldEnemy = wave.getEnemyType();
        Enemy nextEnemy = new Enemy(
                oldEnemy.getPositionY(),
                oldEnemy.getPositionX(),
                oldEnemy.getMaxHealth() + 2,
                oldEnemy.getSpeed() + 2.);
        wave.upgrade(nextEnemy, wave.getEnemyCount() + 4);
    }

    /**
     * Get base's current remaining health.
     *
     * @return health
     */
    public int getHealth() {
        return health;
    }

    public Field getField() {
        return field;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<Tower> getTowers() {
        return towers;
    }

    /**
     * Add tower to game if tower isn't on a road.
     *
     * @param tower     the tower to add
     *
     * @return true if tower was added, otherwise false
     */
    public boolean addTower(Tower tower) {
        if (field.getTileByPosition(tower.getPositionY(), tower.getPositionX()) == Tile.WALL) {
            towers.add(tower);
            return true;
        }
        return false;
    }

    /**
     * This method should only be called by code that deserializes a TowerDefense.
     */
    public void createTransientFields() {
        enemies = new ArrayList<>();
    }

    private void spawnEnemies(double deltaTime) {
        Enemy newEnemy = wave.update(deltaTime);
        if (newEnemy != null) {
            enemies.add(newEnemy);
        }
    }

    private void moveEnemies(double deltaTime) {
        for (Enemy enemy: enemies) {
            Tile under = field.getTileByPosition(enemy.getPositionY(), enemy.getPositionX());
            if (under == Tile.WALL) {
                throw new RuntimeException("Enemy in wall.");
            } else {
                enemy.move(under.y() * deltaTime, under.x() * deltaTime);
            }
        }
    }

    private void checkEnemyPositionsAndHealths() {
        Iterator<Enemy> it = enemies.iterator();
        while (it.hasNext()) {
            Enemy e = it.next();
            if (field.isPositionAtBase(e.getPositionY(), e.getPositionX())) {
                it.remove();
                health--;
            } else if (e.getHealth() <= 0) {
                it.remove();
                // Reward with money
                money += 1;
            }
        }
    }

    private double dist(double y1, double x1, double y2, double x2) {
        double y = y1 - y2;
        double x = x1 - x2;
        return Math.sqrt(y * y + x * x);
    }

    private List<Shot> checkTowerTargets(double deltaTime) {
        List<Shot> shots = new ArrayList<>();
        for (Tower t: towers) {
            if (t.canShoot()) {
                for (Enemy e: enemies) {
                    if (dist(t.getPositionY(), t.getPositionX(), e.getPositionY(), e.getPositionX()) <= t.getRange()) {
                        t.shoot(e);
                        shots.add(new Shot(t.getPositionY(), t.getPositionX(), e.getPositionY(), e.getPositionX()));
                        break;
                    }
                }
            } else {
                t.countCooldown(deltaTime);
            }
        }
        return shots;
    }

    /**
     * Update game state by deltaTime seconds.
     *
     * @param deltaTime        amount of time
     *
     * @return list of shots taken by towers
     */
    public List<Shot> update(double deltaTime) {
        spawnEnemies(deltaTime);

        moveEnemies(deltaTime);

        checkEnemyPositionsAndHealths();

        return checkTowerTargets(deltaTime);
    }

    /**
     * Remove tower price (10 for now) from money and return a new tower.
     *
     * @return a new tower, or null if there isn't enough money
     */
    public Tower buyTower() {
        if (money < towerPrice) {
            return null;
        }
        lastPurchasePrice = towerPrice;
        money -= towerPrice;
        return new Tower(0, 0, 60, 5, 0.8);
    }

    /**
     * Add last tower purchase price to money, works only once per purchase.
     */
    public void refundTower() {
        money += lastPurchasePrice;
        lastPurchasePrice = 0;
    }
}
