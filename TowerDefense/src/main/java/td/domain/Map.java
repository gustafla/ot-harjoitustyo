package td.domain;

import java.lang.IllegalArgumentException;

public class Map {
	private Tile[][] tiles;
	private int height;
	private int width;

	public Map(int height, int width) {
		this.tiles = new Tile[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				this.tiles[i][j] = Tile.WALL;
			}
		}
		this.height = height;
		this.width = width;
	}

	private void generateType0() {
		for (int i = 0; i < width / 2; i++) {
			tiles[height - 1][i] = Tile.ROAD_RIGHT;
		}

		for (int i = height - 1; i >= 0; i--) {
			tiles[i][width / 2] = Tile.ROAD_UP;
		}

		for (int i = width / 2; i < width; i++) {
			tiles[0][i] = Tile.ROAD_RIGHT;
		}
	}

	public Map(int height, int width, int type) {
		this(height, width);

		if (type < 0) {
			throw new IllegalArgumentException("Type cannot be negative");
		}

		if (type > 0) {
			throw new IllegalArgumentException("Available map types are: 0");
		}

		generateType0();
	}

	public Tile getTile(int y, int x) {
		if (x >= width || x < 0 || y >= height || y < 0) {
			throw new IllegalArgumentException();
		}
		return tiles[y][x];
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
