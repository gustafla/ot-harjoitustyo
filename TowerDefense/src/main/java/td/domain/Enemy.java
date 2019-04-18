package td.domain;

/**
 * This class represents an enemy on the game environment that towers can shoot.
 */
public class Enemy {
	private double positionY;
	private double positionX;
	private double maxHealth;
	private double health;
	private double speed;

	public Enemy(double y, double x, double maxHealth, double speed) {
		this.positionY = y;
		this.positionX = x;
		this.maxHealth = maxHealth;
		this.health = maxHealth;
		this.speed = speed;
	}

	/**
	 * @return y-component of position in the map space
	 */
	public double getPositionY() {
		return positionY;
	}

	/**
	 * @return x-component of position in the map space
	 */
	public double getPositionX() {
		return positionX;
	}

	/**
	 * Max health is the amount of health Enemy spawns with.
	 * 
	 * @return max health
	 */
	public double getMaxHealth() {
		return maxHealth;
	}

	/**
	 * Health is the Enemy's current amount of health which may be lowered by
	 * taking damage.
	 *
	 * @return health
	 */
	public double getHealth() {
		return health;
	}

	/**
	 * Lower Enemy's health, health cannot fall below 0.
	 *
	 * @param amount	amount of health to remove
	 */
	public void takeDamage(double amount) {
		health -= amount;
		if (health < 0.) {
			health = 0.;
		}
	}

	/**
	 * Translate position in the map space.
	 *
	 * @param y	the y-component which will be added to positionY
	 * @param x	the x-component which will be added to positionX
	 */
	public void move(double y, double x) {
		positionY += y * speed;
		positionX += x * speed;
	}

	/**
	 * @return a new copy of the Enemy with full health
	 */
	@Override
	public Enemy clone() {
		return new Enemy(
				positionY,
				positionX,
				maxHealth,
				speed
				);
	}
}
