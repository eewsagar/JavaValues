//Java Program to Add, subtract, multiply and divide Two Numbers
public class Method01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello world !!");
		int x=10,y=20;
		addMethod(x, y); // calling of method
		addMethod(x, y);
		
		minusMethod(x, y);
		int output =  multiplyMethod(x, y); //200
		System.out.println(output);
		
		System.out.println(divisionMethod(x, y));
		
		addMethod(x, y);
		addMethod(x, y);
	}
	
	public static void addMethod(int a, int b) {
		System.out.println(a+b);
	}
	
	public static void minusMethod(int a, int b) {
		System.out.println(a-b);
	}
	// access_specifier return_type method_name(parameters){}
	public static int multiplyMethod(int x, int  y) {
		return x*y;
	}
	
	public static double divisionMethod(int a , int b) {
		return b/a;
		
	}
}
