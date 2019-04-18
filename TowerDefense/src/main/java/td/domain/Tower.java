package td.domain;

/**
 * This class represents a Tower on the game environment that can shoot enemies
 * and be placed on the map by the player.
 */
public class Tower {
	private double range;
	private double power;
	private double cooldown;
	private double cooldownNow;

	/**
	 * Constructs a new Tower.
	 *
	 * @param range		shooting reach in sub-tile units
	 * @param power		amount of damage per shot
	 * @param cooldown	delay time between shots in seconds
	 */
	public Tower(double range, double power, double cooldown) {
		this.range = range;
		this.power = power;
		this.cooldown = cooldown;
		this.cooldownNow = 0.;
	}

	public double getRange() {
		return range;
	}

	/**
	 * Check if tower has cooled down for shooting.
	 *
	 * @return true if cooldown timer has expired since last shot, otherwise
	 * false
	 */
	public boolean canShoot() {
		return cooldownNow <= 0.;
	}

	/**
	 * If cooldown is active, advance the cooldown timer
	 *
	 * @param t		time to advance the cooldown timer by (seconds)
	 */
	public void countCooldown(double t) {
		if (!canShoot()) {
			cooldownNow -= t;
		}
	}

	/**
	 * If can shoot, damage enemy by power and activate cooldown.
	 *
	 * @param enemy		enemy to damage
	 */
	public void shoot(Enemy enemy) {
		if (canShoot()) {
			cooldownNow += cooldown;
			enemy.takeDamage(power);
		}
	}
}
