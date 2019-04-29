package td.domain;

import java.lang.IllegalArgumentException;
import java.io.Serializable;

/**
 * This class represents the game environment on which the enemies can walk and towers can be placed on.
 */
@SuppressWarnings("serial")
public class Field implements Serializable {
    private Tile[][] tiles;
    private int height;
    private int width;
    private double tileSize;
    private double spawnPositionY;
    private double spawnPositionX;
    private int baseTileX;
    private int baseTileY;

    /**
     * Constructs a new uninitialized Field.
     *
     * @param height    number of tiles in the y-direction
     * @param width     number of tiles in the x-direction
     * @param tileSize  the ratio of sub-tile units to tiles per dimension
     */
    public Field(int height, int width, double tileSize) {
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
        tiles[height - 1][width / 2] = Tile.ROAD_NE;

        for (int i = height - 2; i > 0; i--) {
            tiles[i][width / 2] = Tile.ROAD_UP;
        }
        tiles[0][width / 2] = Tile.ROAD_NE;

        for (int i = width / 2 + 1; i < width; i++) {
            tiles[0][i] = Tile.ROAD_RIGHT;
        }

        spawnPositionY = height * tileSize - tileSize / 2;
        spawnPositionX = tileSize / 2;
        baseTileY = 0;
        baseTileX = width - 1;
    }

    /**
     * Constructs a new procedurally generated Field.
     *
     * @param height        number of tiles in the y-direction
     * @param width         number of tiles in the x-direction
     * @param tileSize      the ratio of sub-tile units to tiles per dimension
     * @param type          type of field to generate (can only be 0)
     */
    public Field(int height, int width, double tileSize, int type) {
        this(height, width, tileSize);

        if (type < 0) {
            throw new IllegalArgumentException("Type cannot be negative");
        }

        if (type > 0) {
            throw new IllegalArgumentException("Available map types are: 0");
        }

        generateType0();
    }

    /**
     * Get tile by coordinates.
     *
     * @param y        tile coordinate y-component (0 - height-1)
     * @param x        tile coordinate x-component (0 - width-1)
     *
     * @return Tile
     */
    public Tile getTile(int y, int x) {
        if (x >= width || x < 0 || y >= height || y < 0) {
            throw new IllegalArgumentException("Tile (y = " + y + ", x = " + x + ") doesn't exist.");
        }
        return tiles[y][x];
    }

    /**
     * Get tile by sub-tile coordinates.
     *
     * @param y        sub-tile position y-component (0 - height*tileSize-EPSILON)
     * @param x        sub-tile position x-component (0 - width*tileSize-EPSILON)
     *
     * @return Tile
     */
    public Tile getTileByPosition(double y, double x) {
        return getTile((int) (y / tileSize), (int) (x / tileSize));
    }

    /**
     * Get number of tiles in y-direction.
     *
     * @return height in tiles
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get number of tiles in x-direction.
     *
     * @return width in tiles
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get ratio of sub-tile units to tiles.
     *
     * @return tile size
     */
    public double getTileSize() {
        return tileSize;
    }

    /**
     * Get spawn position (sub-tile) y-component.
     *
     * @return spawn position y-component
     */
    public double getSpawnPositionY() {
        return spawnPositionY;
    }

    /**
     * Get spawn position (sub-tile) x-component.
     *
     * @return spawn position x-component
     */
    public double getSpawnPositionX() {
        return spawnPositionX;
    }

    /**
     * Get base tile coordinate (tile space) y-component.
     *
     * @return base tile coordinate y-component
     */
    public int getBaseTileY() {
        return baseTileY;
    }

    /**
     * Get base tile coordinate (tile space) x-component.
     *
     * @return base tile coordinate x-component
     */
    public int getBaseTileX() {
        return baseTileX;
    }

    /**
     * Check if sub-tile coordinates match the base tile location.
     *
     * @param y        position y-component
     * @param x        position x-component
     *
     * @return true if position is on the base tile, otherwise false
     */
    public boolean isPositionAtBase(double y, double x) {
        return (int) (y / tileSize) == baseTileY
            && (int) (x / tileSize) == baseTileX;
    }
}
