package td.domain;

/**
 * This class represents a Tower on the game environment that can shoot enemies
 * and be placed on the map by the player.
 */
public class Tower {
	private int tilePositionY;
	private int tilePositionX;
	private double range;
	private double power;
	private double cooldown;
	private double cooldownNow;

	public Tower(int y, int x, double range, double power, double cooldown) {
		this.tilePositionY = y;
		this.tilePositionX = x;
		this.range = range;
		this.power = power;
		this.cooldown = cooldown;
		this.cooldownNow = 0.;
	}

	public int getTilePositionY() {
		return tilePositionY;
	}

	public int getTilePositionX() {
		return tilePositionX;
	}

	public double getRange() {
		return range;
	}

	public boolean canShoot() {
		return cooldownNow <= 0.;
	}

	public void countCooldown(double t) {
		if (!canShoot()) {
			cooldownNow -= t;
		}
	}

	public void shoot(Enemy enemy) {
		if (canShoot()) {
			cooldownNow += cooldown;
			enemy.takeDamage(power);
		}
	}
}
