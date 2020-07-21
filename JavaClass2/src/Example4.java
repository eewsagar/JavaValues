
public class Example4 {

	public static void main(String[] args) {
		int a, b, c;

		a = 3;
		b = 4;
		c = 5;
		Triangle1 t = new Triangle1(a, b, c);
		System.out.println("Area of Triangle :  " + t.getArea());
		System.out.println("Perimeter of Triangle: " + t.getPerimeter());

	}

}

class Triangle1 {
	// Triangle1(){}
	int a, b, c;

	Triangle1(int a1, int b1, int c1) {
		a=a1;b=b1;c=c1;

		
	}

	public double getArea() {
		double s = (a + b + c) / 2.0;

		return Math.pow((s * (s - a) * (s - b) * (s - c)), .5);
	}

	public double getPerimeter() {
		double p = a + b + c;
		return p;
	}
}
