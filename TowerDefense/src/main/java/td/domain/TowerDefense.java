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

	private double frac(double num) {
		long i = (long) num;
		return num - i;
	}

	private Tile nearestNeighborTileOnAxis(double tileY, double tileX, int y, int x) {
		double coord = tileY * y + tileX * x;
		if (frac(coord) < 0.5) {
			if (coord - 1 < 0) return null;
			return map.getTile((int) tileY - 1 * y, (int) tileX - 1 * x);
		}
		if (coord + 1 >= map.getHeight() * y + map.getWidth() * x) return null;
		return map.getTile((int) tileY + 1 * y, (int) tileX + 1 * x);
	}

	private void moveEnemy(Enemy enemy, double deltaTime, Tile[] tiles) {
		double moveY = 0;
		double moveX = 0;
		for (Tile t: tiles) {
			if (t != null) {
				moveY += t.y();
				moveX += t.x();
			}
		}
		enemy.move(moveY * deltaTime, moveX * deltaTime);
	}

	private void moveEnemies(double deltaTime) {
		Tile[] tiles = new Tile[3];
		for (Enemy enemy: enemies) {
			double underY = map.getTileCoordinateFromPosition(enemy.getPositionY());
			double underX = map.getTileCoordinateFromPosition(enemy.getPositionX());
			Tile under = map.getTile((int) underY, (int) underX);
			if (under == Tile.WALL) {
				throw new RuntimeException("Enemy in wall.");
			} else {
				tiles[0] = under;
				tiles[1] = nearestNeighborTileOnAxis(underY, underX, 1, 0);
				tiles[2] = nearestNeighborTileOnAxis(underY, underX, 0, 1);
				moveEnemy(enemy, deltaTime, tiles);
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
