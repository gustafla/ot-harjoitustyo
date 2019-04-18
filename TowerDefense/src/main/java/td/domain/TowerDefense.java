package td.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * This class encapsulates a Tower Defense game's mechanics.
 */
public class TowerDefense {
	private int money;
	private int health;
	private Field field;
	private Wave wave;
	private List<Enemy> enemies;

	/**
	 * Constructs a new TowerDefense.
	 *
	 * @param field		the field on which to play
	 */
	public TowerDefense(Field field) {
		this.money = 1000;
		this.health = 1000;
		this.field = field;
		this.enemies = new ArrayList<>(100);

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
	 * @return true if no more enemies alive and wave's enemy spawning has
	 * ended, otherwise false
	 */
	public boolean isWaveOver() {
		return enemies.isEmpty() && wave.isSpawningFinished();
	}

	/**
	 * Start next wave.
	 */
	public void nextWave() {
		wave.upgrade(wave.getEnemyType(), wave.getEnemyCount() + 10);
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

	/**
	 * Update game state by deltaTime seconds.
	 *
	 * @param deltaTime		amount of time
	 */
	public void update(double deltaTime) {
		spawnEnemies(deltaTime);

		moveEnemies(deltaTime);

		// check for enemies who have reached the base
		Iterator<Enemy> it = enemies.iterator();
		while (it.hasNext()) {
			Enemy e = it.next();
			if (field.isPositionAtBase(e.getPositionY(), e.getPositionX())) {
				it.remove();
				health--;
			}
		}
	}
}
