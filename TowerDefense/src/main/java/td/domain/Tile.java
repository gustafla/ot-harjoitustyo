package td.domain;

/** The types of an element on the field grid. */
public enum Tile {
    /** Wall, on which enemies shouldn't walk on and towers should be placed on. */
    WALL(0, 0),
    /** Road which directs enemies north. */
    ROAD_UP(-1, 0),
    /** Road which directs enemies south. */
    ROAD_DOWN(1, 0),
    /** Road which directs enemies west. */
    ROAD_LEFT(0, -1),
    /** Road which directs enemies east. */
    ROAD_RIGHT(0, 1),
    /** Road which directs enemies northeast. */
    ROAD_NE(-1, 1),
    /** Road which directs enemies southeast. */
    ROAD_SE(1, 1),
    /** Road which directs enemies southwest. */
    ROAD_SW(1, -1),
    /** Road which directs enemies northwest. */
    ROAD_NW(-1, -1);

    private int y;
    private int x;

    Tile(int y, int x) {
        this.y = y;
        this.x = x;
    }

    /**
     * Get the y-component of this tile's direction for enemies.
     *
     * @return direction y-component
     */
    public int y() {
        return y;
    }

    /**
     * Get the x-component of this tile's direction for enemies.
     *
     * @return direction x-component
     */
    public int x() {
        return x;
    }
}
