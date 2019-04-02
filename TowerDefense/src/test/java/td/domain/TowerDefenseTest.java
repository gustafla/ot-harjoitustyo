package td.domain;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TowerDefenseTest {
	TowerDefense td;

	@Before
	public void setUp() {
		td = new TowerDefense();
	}

	@Test
	public void constructedWith1000Money() {
		assertEquals(td.getMoney(), 1000);
	}

	@Test
	public void constructedWithWave1() {
		assertEquals(td.getWave(), 1);
	}

	@Test
	public void constructedWith1000Health() {
		assertEquals(td.getHealth(), 1000);
	}
}
