package td.domain;

public enum Tile {
	WALL(0, 0),
	ROAD_UP(-1, 0),
	ROAD_DOWN(1, 0),
	ROAD_LEFT(0, -1),
	ROAD_RIGHT(0, 1);

	private int y;
	private int x;

	Tile(int y, int x) {
		this.y = y;
		this.x = x;
	}

	public int y() {
		return y;
	}

	public int x() {
		return x;
	}
}
