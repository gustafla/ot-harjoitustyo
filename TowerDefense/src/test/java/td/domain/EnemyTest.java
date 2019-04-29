package td.domain;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class EnemyTest {
    Enemy enemy;

    private final double EPSILON = 0.00001;

    @Before
    public void setUp() {
        enemy = new Enemy(0, 0, 10, 10);
    }

    @Test
    public void enemyMovesPositive() {
        enemy.move(2, 4);
        assertEquals(20, enemy.getPositionY(), EPSILON);
        assertEquals(40, enemy.getPositionX(), EPSILON);
    }

    @Test
    public void enemyMovesNegative() {
        enemy.move(-1, -1.5);
        assertEquals(-10, enemy.getPositionY(), EPSILON);
        assertEquals(-15, enemy.getPositionX(), EPSILON);
    }

    @Test
    public void enemyHasMaxHealth() {
        assertEquals(enemy.getMaxHealth(), enemy.getHealth(), EPSILON);
    }

    @Test
    public void enemyTakesDamage() {
        enemy.takeDamage(5.5);
        assertEquals(4.5, enemy.getHealth(), EPSILON);
    }

    @Test
    public void enemyCannotBeDamagedUnder0() {
        enemy.takeDamage(15.5);
        assertEquals(0, enemy.getHealth(), EPSILON);
        enemy.takeDamage(15.5);
        assertEquals(0, enemy.getHealth(), EPSILON);
    }

    @Test
    public void enemyCloneHasSameMaxHealth() {
        enemy.takeDamage(2);
        Enemy clone = enemy.clone();
        assertEquals(enemy.getMaxHealth(), clone.getMaxHealth(), EPSILON);
    }

    @Test
    public void enemyCloneHasMaxHealth() {
        enemy.takeDamage(2);
        Enemy clone = enemy.clone();
        assertEquals(enemy.getMaxHealth(), clone.getHealth(), EPSILON);
    }

    @Test
    public void enemyCloneHasSamePosition() {
        enemy.move(4.6, -23.01);
        Enemy clone = enemy.clone();
        assertEquals(enemy.getPositionX(), clone.getPositionX(), EPSILON);
        assertEquals(enemy.getPositionY(), clone.getPositionY(), EPSILON);
    }
}
