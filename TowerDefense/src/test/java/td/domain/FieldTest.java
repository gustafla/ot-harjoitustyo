package td.domain;

import java.lang.IllegalArgumentException;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class FieldTest {
	Field field;

	private final double EPSILON = 0.00001;

	@Before
	public void setUp() {
		field = new Field(32, 64, 10, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getNegativeYThrowsIllegalArgEx() {
		field.getTile(-2, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getNegativeXThrowsIllegalArgEx() {
		field.getTile(0, -1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getOverflowYThrowsIllegalArgEx() {
		field.getTile(100, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getOverflowXThrowsIllegalArgEx() {
		field.getTile(0, 200);
	}

	@Test
	public void type0ContainsRoadTiles() {
		assertEquals(Tile.ROAD_RIGHT, field.getTile(0, 63));
	}

	@Test
	public void type0ContainsWallTiles() {
		assertEquals(Tile.WALL, field.getTile(0, 0));
	}

	@Test
	public void getTileSize() {
		assertEquals(10, field.getTileSize(), EPSILON);
	}

	@Test
	public void getWidth() {
		assertEquals(64, field.getWidth());
	}

	@Test
	public void getHeight() {
		assertEquals(32, field.getHeight());
	}

	@Test
	public void type0BasePositionIsAtBase() {
		double y = field.getBaseTileY() * field.getTileSize();
		double x = field.getBaseTileX() * field.getTileSize();
		assertFalse(field.isPositionAtBase(y + 11, x));
		assertFalse(field.isPositionAtBase(y + 0.1, x + 11));
		assertTrue(field.isPositionAtBase(y + 0.1, x + 0.1));
	}

	@Test
	public void type0GetTileByPosition() {
		double y = field.getHeight() * field.getTileSize() * 0.5;
		double x = field.getWidth() * field.getTileSize() * 0.5;
		assertEquals(Tile.ROAD_UP, field.getTileByPosition(y, x));
	}

	@Test(expected = IllegalArgumentException.class)
	public void fieldTypeCannotBeNegative() {
		field = new Field(0, 0, 0, -1);
	}
}
