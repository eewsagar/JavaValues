import java.util.Scanner;

public class Example6 {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Please enter the length of Rectangle: ");
		int len =  s.nextInt();
		System.out.println("Please enter the breadth of Rectangle: ");
		int bre =  s.nextInt();
		Area a = new Area();
		System.out.println("Area of Rectangle is: "+a.setDim(len, bre))
		;
	}

}
class Area{
	
	int lenght;
	int breadth;
	public int setDim(int l, int b) {
		this.lenght = l;
		this.breadth = b;
		return getLenght();
	}
	
	public int getLenght() {
		return lenght*breadth;
	}
	
	
	
}
