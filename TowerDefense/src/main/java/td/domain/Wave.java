package td.domain;

import java.io.Serializable;

/**
 * This class represents a wave of multiple enemies which will spawn one by one,
 * that must be beaten by the player's towers.
 */
@SuppressWarnings("serial")
public class Wave implements Serializable {
	private int number;
	private Enemy enemyType;
	private int enemyCount;
	private double enemySpawnCooldown;
	private double enemySpawnCooldownNow;
	private int enemiesSpawned;

	/**
	 * Construct a new Wave.
	 *
	 * @param enemyType		enemy to clone when spawning a new enemy
	 * @param enemyCount	number of enemies to spawn
	 */
	public Wave(Enemy enemyType, int enemyCount) {
		this.number = 0;
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

	/**
	 * Check if enemy spawning is finished.
	 *
	 * @return true if all enemies have been spawned, otherwise false
	 */
	public boolean isSpawningFinished() {
		return enemiesSpawned >= enemyCount;
	}

	/**
	 * Advance spawn timer.
	 *
	 * @param deltaTime		time to advance the spawning by (in seconds)
	 *
	 * @return enemy if an enemy is spawned, otherwise null
	 */
	public Enemy update(double deltaTime) {
		if (!isSpawningFinished()) {
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

	/**
	 * Upgrade wave to next.
	 *
	 * @param enemyType		next wave's spawnable enemy
	 * @param enemyCount	next wave's number of enemies to spawn
	 */
	public void upgrade(Enemy enemyType, int enemyCount) {
		this.number++;
		this.enemyType = enemyType;
		this.enemyCount = enemyCount;
		this.enemiesSpawned = 0;
	}
}
