package td.domain;

import java.lang.IllegalArgumentException;

public class Map {
	private Tile[][] tiles;
	private int h;
	private int w;

	public Map(int h, int w) {
		this.tiles = new Tile[h][w];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				this.tiles[i][j] = Tile.WALL;
			}
		}
		this.h = h;
		this.w = w;
	}

	private void generateType0() {
		for (int i = 0; i < w / 2; i++) {
			tiles[h - 1][i] = Tile.ROAD_RIGHT;
		}

		for (int i = h - 1; i >= 0; i--) {
			tiles[i][w / 2] = Tile.ROAD_UP;
		}

		for (int i = w / 2; i < w - 1; i++) {
			tiles[0][i] = Tile.ROAD_RIGHT;
		}
	}

	public Map(int h, int w, int type) {
		this(h, w);

		if (type < 0) {
			throw new IllegalArgumentException("Type cannot be negative");
		}

		if (type > 0) {
			throw new IllegalArgumentException("Available map types are: 0");
		}

		generateType0();
	}

	public Tile getTile(int y, int x) {
		if (x >= w || x < 0 || y >= h || y < 0) {
			throw new IllegalArgumentException();
		}
		return tiles[y][x];
	}
}
