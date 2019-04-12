package td.domain;

public class Wave {
	private int number;
	private Enemy enemyType;
	private int enemyCount;
	private double enemySpawnCooldown;
	private double enemySpawnCooldownNow;
	private int enemiesSpawned;

	public Wave(Enemy enemyType, int enemyCount) {
		this.number = 1;
		this.enemyType = enemyType;
		this.enemyCount = enemyCount;
		this.enemySpawnCooldown = 0.3;
		this.enemySpawnCooldownNow = 0.0;
		this.enemiesSpawned = 0;
	}

	public int getNumber() {
		return number;
	}

	public Enemy getEnemyType() {
		return enemyType;
	}

	public int getEnemyCount() {
		return enemyCount;
	}

	public boolean finishedSpawning() {
		return enemiesSpawned >= enemyCount;
	}

	public Enemy update(double deltaTime) {
		if (!finishedSpawning()) {
			if (enemySpawnCooldownNow >= 0.) {
				enemySpawnCooldownNow -= deltaTime;
			} else {
				enemySpawnCooldownNow = enemySpawnCooldown;
				enemiesSpawned++;
				return enemyType.clone();
			}
		}
		return null;
	}

	public void upgrade(Enemy enemyType, int enemyCount) {
		this.number++;
		this.enemyType = enemyType;
		this.enemyCount = enemyCount;
	}
}
