


public class Example5 {

	public static void main(String[] args) {
		
		Rectangle r1 = new Rectangle(4,5);
		Rectangle r2 = new Rectangle(5,8);
		System.out.println("Area of Rectangle :  " + r1.getArea()+
				"Perimeter of Rectangle: " + r1.getPerimeter());
		System.out.println("Area of Rectangle :  " + r2.getArea()+" "
				+ "Perimeter of Rectangle: " + r2.getPerimeter());

	}

}

class Rectangle {
	
	int lenght, breadth;

	public Rectangle(int l, int b) {
		// TODO Auto-generated constructor stub
		lenght=l;
		breadth=b;
	}

	public double getArea() {
		

		return lenght*breadth;
	}

	public double getPerimeter() {
		
		return 2*(lenght+breadth);
	}
}

