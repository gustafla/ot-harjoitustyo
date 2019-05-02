package td.domain;

/** Stores the coordinates and time elapsed of shots taken by towers (needed for rendering). */
public class Shot {
    private double y1;
    private double x1;
    private double y2;
    private double x2;
    private double time;

    /**
     * Constructs a new Shot.
     *
     * @param y1    first point's y-coordinate
     * @param x1    first point's x-coordinate
     * @param y2    second point's y-coordinate
     * @param x2    second point's x-coordinate
     */
    public Shot(double y1, double x1, double y2, double x2) {
        this.y1 = y1;
        this.x1 = x1;
        this.y2 = y2;
        this.x2 = x2;
        this.time = 0;
    }

    public double getY1() {
        return y1;
    }

    public double getX1() {
        return x1;
    }

    public double getY2() {
        return y2;
    }

    public double getX2() {
        return x2;
    }

    /**
     * Update timer.
     *
     * @param deltaTime     time in seconds to update the timer by
     */
    public void updateTime(double deltaTime) {
        time += deltaTime;
    }

    public double getTime() {
        return time;
    }
}
