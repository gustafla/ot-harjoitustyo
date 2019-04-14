package td.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class TowerDefense {
	private int money;
	private int health;
	private Map map;
	private Wave wave;
	private List<Tower> towers;
	private List<Enemy> enemies;

	public TowerDefense(Map map) {
		this.money = 1000;
		this.health = 1000;
		this.map = map;
		this.enemies = new ArrayList<>(100);
		this.towers = new ArrayList<>(20);

		Enemy firstEnemyType = new Enemy(
				map.getSpawnPositionY(),
				map.getSpawnPositionX(),
				10, // health,
				40.); // speed
		this.wave = new Wave(firstEnemyType, 0);
	}

	public int getMoney() {
		return money;
	}

	public int getWaveNumber() {
		return wave.getNumber();
	}

	public boolean waveIsOver() {
		return enemies.isEmpty() && wave.hasFinishedSpawning();
	}

	public void nextWave() {
		wave.upgrade(wave.getEnemyType(), wave.getEnemyCount() + 10);
	}
	
	public int getHealth() {
		return health;
	}

	public Map getMap() {
		return map;
	}

	public List<Enemy> getEnemies() {
		return enemies;
	}

	public List<Tower> getTowers() {
		return towers;
	}

	public void addTower(Tower tower) {
		towers.add(tower);
	}

	private void spawnEnemies(double deltaTime) {
		Enemy newEnemy = wave.update(deltaTime);
		if (newEnemy != null) {
			enemies.add(newEnemy);
		}
	}

	private void moveEnemies(double deltaTime) {
		for (Enemy enemy: enemies) {
			Tile under = map.getTileByPosition(enemy.getPositionY(), enemy.getPositionX());
			if (under == Tile.WALL) {
				throw new RuntimeException("Enemy in wall.");
			} else {
				enemy.move(under.y() * deltaTime, under.x() * deltaTime);
			}
		}
	}

	public void update(double deltaTime) {
		spawnEnemies(deltaTime);

		moveEnemies(deltaTime);

		// check for enemies who have reached the base
		Iterator<Enemy> it = enemies.iterator();
		while (it.hasNext()) {
			Enemy e = it.next();
			if (map.positionIsAtBase(e.getPositionY(), e.getPositionX())) {
				it.remove();
				health--;
			}
		}

		// check all towers for ready to shoot and find enemy for each in range
		for (Tower tower: towers) {
			;
		}
		
	}
}
