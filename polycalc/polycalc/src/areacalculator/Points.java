package areacalculator;

public class Points {

	protected int[] xPoints;
	protected int[] yPoints;
	protected double area;

	Points() {
//		this.xPoints = new int[] { 350, 350, 550, 550 };
//		this.yPoints = new int[] { 150, 350, 350, 150 };
		this.area = 40000;
//		calculateArea();
	}

	protected void calculateArea() {
		area = 0;
		for (int i = 0; i < 3; i++) {
			area += xPoints[i] * yPoints[i + 1] - xPoints[i + 1] * yPoints[i];
		}
		area += xPoints[3] * yPoints[0] - xPoints[0] * yPoints[3];
		area = Math.abs(area) / 2.0f;
	}
	
	public void setXs(int pt[]) {
		this.xPoints = pt;
	}
	
	public void setYs(int pt[]) {
		this.yPoints = pt;
	}

	public int getXpt(int i) {
		return xPoints[i];
	}

	public int getYpt(int i) {
		return yPoints[i];
	}

	public int[] getXs() {
		return xPoints;
	}

	public int[] getYs() {
		return yPoints;
	}

	public double getArea() {
		return area;
	}
	
	

}
