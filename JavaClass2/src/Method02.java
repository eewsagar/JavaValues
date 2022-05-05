//Java Program to Find Quotient and Remainder
// method overloading
public class Method02 {
	public static void main(String[] args) {
		int x=  10, y =5;
		double output =  divisionMethod(x, y);
		System.out.println(output);
		
		System.out.println(divisionMethod(100.2, 20.05));  // argument
		
		double output2 =  divisionMethod(x);
		System.out.println(output2);
	}
	
	public static double divisionMethod(int x, int y) {
		double quotient =  x/y;
		return quotient;
	}
	
	public static double divisionMethod(double x, double y) {
		System.out.println("We are in side 2nd division method");
		double quotient =  x/y;
		return quotient;
	}
	
	public static double divisionMethod(int x) {
		double remainder =  x%9;  // % modulus
		return remainder;
	}

}
