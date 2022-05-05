//Java Program to Display Fibonacci Series - 0,1,1,2,3,5,8,13.. till counts
// one with for loop and another with recursion
public class Method04 {
	
	public static void main(String[] args) {
		febonacciMethod(10);
		System.out.print("\n Recursion Febonacci series : "+ num1+ ", "+num2+", ");
		recursionFeboMethod(8);
	}
	
	public static void febonacciMethod(int count) {
		 int num1=0, num2=1, num3=0;
		System.out.print("Febonacci series : "+ num1+ ", "+num2);
		
		for(int i = 3; i <= count;i++) {
			num3 = num1 + num2;
			System.out.print(" , "+num3);
			// swaping
			num1 =num2;
			num2 = num3;
		}
	}
	static int num1=0, num2=1, num3=0;
	public static void recursionFeboMethod(int count) {
		if(count>0) {
			num3 = num1+num2;
			System.out.print(num3+" ,");
			num1 =num2;
			num2= num3;
			recursionFeboMethod(count-1); // recursion
		}
		
	}

}
