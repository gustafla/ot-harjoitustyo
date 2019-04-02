package td.domain;

public class TowerDefense {
	private int money;
	private int wave;
	private int health;

	public TowerDefense() {
		money = 1000;
		wave = 1;
		health = 1000;
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
}
