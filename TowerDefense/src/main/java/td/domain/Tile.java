package td.domain;

/**
 * This enumeration represents the types of an element on the field grid.
 */
public enum Tile {
	/** Wall, on which Enemies shouldn't walk on and Towers should be placed on. */
	WALL(0, 0),
	/** Road which directs Enemies north. */
	ROAD_UP(-1, 0),
	/** Road which directs Enemies south. */
	ROAD_DOWN(1, 0),
	/** Road which directs Enemies west. */
	ROAD_LEFT(0, -1),
	/** Road which directs Enemies east. */
	ROAD_RIGHT(0, 1),
	/** Road which directs Enemies northeast. */
	ROAD_NE(-1, 1),
	/** Road which directs Enemies southeast. */
	ROAD_SE(1, 1),
	/** Road which directs Enemies southwest. */
	ROAD_SW(1, -1),
	/** Road which directs Enemies northwest. */
	ROAD_NW(-1, -1);

	private int y;
	private int x;

	Tile(int y, int x) {
		this.y = y;
		this.x = x;
	}

	/**
	 * Get the y-component of this Tile's direction for enemies.
	 *
	 * @return direction y-component
	 */
	public int y() {
		return y;
	}

	/**
	 * Get the x-component of this Tile's direction for enemies.
	 *
	 * @return direction x-component
	 */
	public int x() {
		return x;
	}
}
