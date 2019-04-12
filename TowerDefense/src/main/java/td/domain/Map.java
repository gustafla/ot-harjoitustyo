package td.domain;

import java.lang.IllegalArgumentException;

public class Map {
	private Tile[][] tiles;
	private int height;
	private int width;
	private double tileSize;
	private double spawnPositionY;
	private double spawnPositionX;
	private int baseTileX;
	private int baseTileY;

	public Map(int height, int width, double tileSize) {
		this.tiles = new Tile[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				this.tiles[i][j] = Tile.WALL;
			}
		}
		this.height = height;
		this.width = width;
		this.tileSize = tileSize;
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

		spawnPositionY = height * tileSize - tileSize / 2;
		spawnPositionX = tileSize / 2;
		baseTileY = 0;
		baseTileX = width - 1;
	}

	public Map(int height, int width, double tileSize, int type) {
		this(height, width, tileSize);

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
			throw new IllegalArgumentException("Tile (y = " + y + ", x = " + x + ") doesn't exist.");
		}
		return tiles[y][x];
	}

	public Tile getTileFromPosition(double y, double x) {
		return getTile((int) (y / tileSize), (int) (x / tileSize));
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public double getTileSize() {
		return tileSize;
	}

	public double getSpawnPositionY() {
		return spawnPositionY;
	}

	public double getSpawnPositionX() {
		return spawnPositionX;
	}

	public boolean isPositionAtBase(double y, double x) {
		return (int) (y / tileSize) == baseTileY
			&& (int) (x / tileSize) == baseTileX;
	}
}
