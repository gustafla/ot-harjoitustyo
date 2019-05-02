package td.domain;

import java.io.Serializable;

/**
 * A Tower that can shoot enemies and be placed on the field by the player.
 */
@SuppressWarnings("serial")
public class Tower implements Serializable {
    private double positionY;
    private double positionX;
    private double range;
    private double power;
    private double cooldown;
    private double cooldownNow;

    /**
     * Constructs a new Tower.
     *
     * @param y         position y
     * @param x         position x
     * @param range     shooting reach in sub-tile units
     * @param power     amount of damage per shot
     * @param cooldown  delay time between shots in seconds
     */
    public Tower(double y, double x, double range, double power, double cooldown) {
        this.positionY = y;
        this.positionX = x;
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
     * @return true if cooldown timer has expired since last shot, otherwise false
     */
    public boolean canShoot() {
        return cooldownNow <= 0.;
    }

    /**
     * If cooldown is active, advance the cooldown timer
     *
     * @param t        time to advance the cooldown timer by (seconds)
     */
    public void countCooldown(double t) {
        if (!canShoot()) {
            cooldownNow -= t;
        }
    }

    /**
     * If can shoot, damage enemy by power and activate cooldown.
     *
     * @param enemy        enemy to damage
     */
    public void shoot(Enemy enemy) {
        if (canShoot()) {
            cooldownNow = cooldown;
            enemy.takeDamage(power);
        }
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }
}
