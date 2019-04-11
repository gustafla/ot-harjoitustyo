package td.domain;

public class Enemy {
	private double positionX;
	private double positionY;
	private double health;
	private double healthNow;
	private double speed;

	public Enemy(double x, double y, double health, double speed) {
		this.positionX = x;
		this.positionY = y;
		this.health = health;
		this.healthNow = health;
		this.speed = speed;
	}

	public double getPositionX() {
		return positionX;
	}

	public double getPositionY() {
		return positionY;
	}

	public double getHealth() {
		return health;
	}

	public double getHealthNow() {
		return healthNow;
	}

	public void takeDamage(double amount) {
		healthNow -= amount;
		if (healthNow < 0.) {
			healthNow = 0.;
		}
	}
	
	public void move(double x, double y) {
		positionX += x * speed;
		positionY += y * speed;
	}

	@Override
	public Enemy clone() {
		return new Enemy(
				positionX,
				positionY,
				health,
				speed
				);
	}
}
