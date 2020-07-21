import java.util.Scanner;

public class Example8 {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Please enter the 3 number to find the avarage");
		System.out.println("First number : ");
		int a =  s.nextInt();
		System.out.println("second number : ");
		int b =  s.nextInt();
		System.out.println("third number : ");
		int c =  s.nextInt();
		Avarage av = new Avarage(a, b, c);
	}

}
class Avarage{
	int a,b,c;
	Avarage(int a1,int b1,int c1){
		a=a1;b=b1;
		c=c1;
		System.out.println("Avarage of number is : "+(a+b+c)/3);
	}
	
}