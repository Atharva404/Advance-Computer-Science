class Point {
	int x;
	int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "x: " + this.x + "  y: " + this.y;
	}
}
public class test {

	public static void main(String[] args) {
		Point p = new Point(1, 2);
		change(p);
		System.out.println(p);
	}

	private static void change(Point thePoint) {
		// TODO Auto-generated method stub
		thePoint.x = 3;
		thePoint.y = 4;
	}

}
