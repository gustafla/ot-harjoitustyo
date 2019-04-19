package td.domain;

public class Shot {
	private double y1;
	private double x1;
	private double y2;
	private double x2;
	private double time;

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

	public void updateTime(double deltaTime) {
		time += deltaTime;
	}

	public double getTime() {
		return time;
	}
}
