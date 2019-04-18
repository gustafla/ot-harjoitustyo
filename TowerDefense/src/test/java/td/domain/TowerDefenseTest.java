package td.domain;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TowerDefenseTest {
	Field field;
	TowerDefense td;

	@Before
	public void setUp() {
		field = new Field(64, 64, 10, 0);
		td = new TowerDefense(field);
	}

	@Test
	public void constructedWithMoney() {
		assertTrue(0 < td.getMoney());
	}

	@Test
	public void constructedWithWave0() {
		assertEquals(0, td.getWaveNumber());
	}

	@Test
	public void constructedWithHealth() {
		assertTrue(0 < td.getHealth());
	}

	@Test
	public void constructedWithNoEnemies() {
		assertTrue(td.getEnemies().isEmpty());
	}

	@Test
	public void getFieldGivesCorrectField() {
		assertEquals(field, td.getField());
	}

	@Test
	public void enemiesGetSpawned() {
		td.nextWave();
		for (int i = 0; i < 100; i++) {
			td.update(1./60.);
		}
		assertFalse(td.getEnemies().isEmpty());
	}

	@Test(timeout=1000)
	public void waveEndsWithNoEnemies() {
		td.nextWave();
		while (!td.isWaveOver()) {
			td.update(1./30.);
		}
		assertTrue(td.getEnemies().isEmpty());
	}

	@Test(timeout=1000)
	public void waveEndsWithLessHealthAsEnemiesReachBase() {
		int healthBefore = td.getHealth();
		td.nextWave();
		while (!td.isWaveOver()) {
			td.update(1./30.);
		}
		assertTrue(td.getEnemies().isEmpty());
		assertTrue(td.getHealth() < healthBefore);
	}
}
