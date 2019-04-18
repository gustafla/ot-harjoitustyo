package td.domain;

import java.lang.IllegalArgumentException;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MapTest {
	Map map;

	private final double EPSILON = 0.00001;

	@Before
	public void setUp() {
		map = new Map(32, 64, 10, 0);
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
		assertEquals(Tile.ROAD_RIGHT, map.getTile(0, 63));
	}

	@Test
	public void type0ContainsWallTiles() {
		assertEquals(Tile.WALL, map.getTile(0, 0));
	}

	@Test
	public void getTileSize() {
		assertEquals(10, map.getTileSize(), EPSILON);
	}

	@Test
	public void getWidth() {
		assertEquals(64, map.getWidth());
	}

	@Test
	public void getHeight() {
		assertEquals(32, map.getHeight());
	}

	@Test
	public void type0BasePositionIsAtBase() {
		double y = map.getBaseTileY() * map.getTileSize();
		double x = map.getBaseTileX() * map.getTileSize();
		assertFalse(map.isPositionAtBase(y + 11, x));
		assertFalse(map.isPositionAtBase(y + 0.1, x + 11));
		assertTrue(map.isPositionAtBase(y + 0.1, x + 0.1));
	}

	@Test
	public void type0GetTileByPosition() {
		double y = map.getHeight() * map.getTileSize() * 0.5;
		double x = map.getWidth() * map.getTileSize() * 0.5;
		assertEquals(Tile.ROAD_UP, map.getTileByPosition(y, x));
	}

	@Test(expected = IllegalArgumentException.class)
	public void mapTypeCannotBeNegative() {
		map = new Map(0, 0, 0, -1);
	}
}
