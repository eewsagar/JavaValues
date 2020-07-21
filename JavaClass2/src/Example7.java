
import java.util.Scanner;

public class Example7 {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Please enter the length of Rectangle: ");
		int len = s.nextInt();
		System.out.println("Please enter the breadth of Rectangle: ");
		int bre = s.nextInt();
		Area1 a = new Area1(len,bre);
		System.out.println("Area of Rectangle is: " + a.getLenght());
	}

}

class Area1 {

	int lenght;
	int breadth;

	  Area1(int l, int b) {
		this.lenght = l;
		this.breadth = b;
		
	}

	public int getLenght() {
		return lenght * breadth;
	}

}
