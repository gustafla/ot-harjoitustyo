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
	public void constructedWith1000Money() {
		assertEquals(1000, td.getMoney());
	}

	@Test
	public void constructedWithWave1() {
		assertEquals(1, td.getWaveNumber());
	}

	@Test
	public void constructedWith1000Health() {
		assertEquals(1000, td.getHealth());
	}
}
