package td.domain;

import java.lang.IllegalArgumentException;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MapTest {
	Map map;

	@Before
	public void setUp() {
		map = new Map(64, 64, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getNegativeYThrowsIllegalArgEx() {
		map.getTile(-2, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getNegativeXThrowsIllegalArgEx() {
		map.getTile(0, -1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getOverflowYThrowsIllegalArgEx() {
		map.getTile(100, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getOverflowXThrowsIllegalArgEx() {
		map.getTile(0, 200);
	}

	@Test
	public void type0ContainsRoadTiles() {
		assertEquals(map.getTile(63, 0), Tile.ROAD_RIGHT);
	}

	@Test
	public void type0ContainsWallTiles() {
		assertEquals(map.getTile(0, 0), Tile.WALL);
	}
}
