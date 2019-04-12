package td.domain;

public class Enemy {
	private double positionY;
	private double positionX;
	private double health;
	private double healthNow;
	private double speed;

	public Enemy(double y, double x, double health, double speed) {
		this.positionY = y;
		this.positionX = x;
		this.health = health;
		this.healthNow = health;
		this.speed = speed;
	}

	public double getPositionY() {
		return positionY;
	}

	public double getPositionX() {
		return positionX;
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
	
	public void move(double y, double x) {
		positionY += y * speed;
		positionX += x * speed;
	}

	@Override
	public Enemy clone() {
		return new Enemy(
				positionY,
				positionX,
				health,
				speed
				);
	}
}
