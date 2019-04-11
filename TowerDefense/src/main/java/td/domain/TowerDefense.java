package td.domain;

import java.util.ArrayList;
import java.util.List;

public class TowerDefense {
	private int money;
	private int wave;
	private int health;
	private Map map;

	private List<Enemy> enemies;
	private List<Tower> towers;

	public TowerDefense(Map map) {
		this.money = 1000;
		this.wave = 1;
		this.health = 1000;
		this.map = map;
		this.enemies = new ArrayList<>(100);
		this.towers = new ArrayList<>(20);
	}

	public int getMoney() {
		return money;
	}

	public int getWave() {
		return wave;
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

	public List<Enemy> getTowers() {
		return towers;
	}

	public void addTower(Tower tower) {
		towers.add(tower);
	}

	public void update(double deltaTime) {
		// spawn enemies

		// check all towers for ready to shoot and find enemy for each in range
		for (Tower tower: towers) {
		}

		// move all enemies and check for enemies who have reached the base
		for (Enemy enemy: enemies) {
		}
		
	}
}
