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
		this.wave = new Wave(new Enemy(0, 0, 10, 0.6), 20);
		this.enemies = new ArrayList<>(100);
		this.towers = new ArrayList<>(20);
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

	public void update(double deltaTime) {
		// spawn enemies
		Enemy newEnemy = wave.update(deltaTime);
		if (newEnemy != null) {
			enemies.add(newEnemy);
		}

		// check all towers for ready to shoot and find enemy for each in range
		for (Tower tower: towers) {
		}

		// move all enemies and check for enemies who have reached the base
		for (Enemy enemy: enemies) {
		}
		
	}
}
