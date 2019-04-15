package td.domain;

import static org.junit.Assert.*;
import org.junit.Test;

public class TileTest {
	@Test
	public void wallHasNoDirection() {
		assertEquals(0, Tile.WALL.x());
		assertEquals(0, Tile.WALL.y());
	}

	@Test
	public void roadsHaveDirection() {
		Tile[] tiles = {
			Tile.ROAD_UP,
			Tile.ROAD_DOWN,
			Tile.ROAD_LEFT,
			Tile.ROAD_RIGHT,
			Tile.ROAD_NE,
			Tile.ROAD_SE,
			Tile.ROAD_SW,
			Tile.ROAD_NW
		};

		for (Tile t: tiles) {
			assertTrue(t.x() != 0 || t.y() != 0);
		}
	}
}
