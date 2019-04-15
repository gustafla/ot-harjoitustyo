package td.domain;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TowerDefenseTest {
	TowerDefense td;

	@Before
	public void setUp() {
		td = new TowerDefense(new Map(0, 0, 0));
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
}
