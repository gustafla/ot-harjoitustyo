package td.domain;

public enum Tile {
	WALL(0, 0),
	ROAD_UP(-1, 0),
	ROAD_DOWN(1, 0),
	ROAD_LEFT(0, 1),
	ROAD_RIGHT(0, -1);

	private int x;
	private int y;

	Tile(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}
}
