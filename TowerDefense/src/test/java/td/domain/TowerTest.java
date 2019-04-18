package td.domain;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TowerTest {
	Tower tower;
	Enemy enemy;

	private final double EPSILON = 0.00001;

	@Before
	public void setUp() {
		tower = new Tower(10, 5, 1.);
		enemy = new Enemy(0, 0, 10, 10);
	}

	@Test
	public void shootingEnemyDamagesIt() {
		double enemyHealth = enemy.getHealth();
		tower.shoot(enemy);
		assertEquals(enemyHealth - 5, enemy.getHealth(), EPSILON);
	}

	@Test
	public void towerNeedsToCoolDown() {
		tower.shoot(enemy);
		assertFalse(tower.canShoot());
	}

	@Test
	public void towerCoolsDownInCorrectTime() {
		tower.shoot(enemy);
		int i;
		for (i = 0; !tower.canShoot(); i++) {
			tower.countCooldown(0.10001);
		}
		assertEquals(10, i);
	}

	@Test
	public void cannotCooldownWhenCanShoot() {
		tower.shoot(enemy);
		for (int i = 0; i < 30; i++) {
			tower.countCooldown(0.1);
		}
		tower.shoot(enemy);
		assertFalse(tower.canShoot());
	}

	@Test
	public void willNotShootIfCanNotShoot() {
		double enemyHealth = enemy.getHealth();
		tower.shoot(enemy);
		tower.shoot(enemy);
		assertEquals(enemyHealth - 5, enemy.getHealth(), EPSILON);
	}
}
