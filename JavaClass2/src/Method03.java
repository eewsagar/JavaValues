//Scope of variable, recursion
public class Method03 {
	static int  age =100;
	public static void main(String[] args) {
		// System.out.println(x); not in scope
		int x =10; // declare or define
		System.out.println(x); // scope
		if (x<11) {
			System.out.println("x is less than 11"); 
		}
		System.out.println(x+2);
		
		for( int y =10;y>0; y-- ) {
			System.out.println(y);
		}			
		//System.out.println(y); it is out of scope
		System.out.println(age); 
		
		myMethod();
		int data = factorial(5);
		System.out.println("recursion method output : "+data);
	        
	}
	 public static void myMethod() {
		 // variable age is in scope
		 System.out.println("inside other method : "+age); 
	 }
	  // factorial of 5 = 5*4*3*2*1
 // recursion  -Calling itself
	 public static int factorial(int x) {
		int fact =1;
		 while (x>0) {
			  fact =  fact*x;
			  x--;
			  System.out.println("value of x: "+x+ " value of fact : "+fact);
			  factorial(x);
			
		 }
		 return fact;
	 }

}
