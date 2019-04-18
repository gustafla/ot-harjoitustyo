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

	public double getPositionY() {
		return positionY;
	}

	public double getPositionX() {
		return positionX;
	}

	public double getMaxHealth() {
		return maxHealth;
	}

	public double getHealth() {
		return health;
	}

	public void takeDamage(double amount) {
		health -= amount;
		if (health < 0.) {
			health = 0.;
		}
	}

	
	public void move(double y, double x) {
		positionY += y * speed;
		positionX += x * speed;
	}

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
