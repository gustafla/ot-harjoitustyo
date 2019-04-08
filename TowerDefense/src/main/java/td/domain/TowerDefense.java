package td.domain;

public class TowerDefense {
	private int money;
	private int wave;
	private int health;
	private Map map;

	public TowerDefense(Map map) {
		this.money = 1000;
		this.wave = 1;
		this.health = 1000;
		this.map = map;
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
}
