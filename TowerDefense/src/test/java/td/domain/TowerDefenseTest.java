package td.domain;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TowerDefenseTest {
	Map map;
	TowerDefense td;

	@Before
	public void setUp() {
		map = new Map(64, 64, 10, 0);
		td = new TowerDefense(map);
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
	public void constructedWithNoTowers() {
		assertTrue(td.getTowers().isEmpty());
	}

	@Test
	public void constructedWithNoEnemies() {
		assertTrue(td.getEnemies().isEmpty());
	}

	@Test
	public void getMapGivesCorrectMap() {
		assertEquals(map, td.getMap());
	}

	@Test
	public void addTowerWorks() {
		Tower tower = new Tower(0, 0, 10, 600, 0.2);
		td.addTower(tower);
		assertEquals(1, td.getTowers().size());
		assertTrue(td.getTowers().contains(tower));
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
