package td.domain;

import java.util.ArrayList;
import java.util.List;

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
				20.); // speed
		this.wave = new Wave(firstEnemyType, 20);
	}

	public int getMoney() {
		return money;
	}

	public int getWaveNumber() {
		return wave.getNumber();
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
			double y = enemy.getPositionY();
			double x = enemy.getPositionX();
			int underY = map.getTileCoordinateFromPosition(y);
			int underX = map.getTileCoordinateFromPosition(x);
			Tile under = map.getTile(underY, underX);
			if (under == Tile.WALL) {
				throw new RuntimeException("Cannot deal with enemy in wall.");
			} else {
				enemy.move(under.y() * deltaTime, under.x() * deltaTime);
			}
		}
	}

	public void update(double deltaTime) {
		spawnEnemies(deltaTime);

		moveEnemies(deltaTime);

		// check for enemies who have reached the base

		// check all towers for ready to shoot and find enemy for each in range
		for (Tower tower: towers) {
			;
		}
		
	}
}
