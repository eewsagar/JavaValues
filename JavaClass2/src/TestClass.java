
public class TestClass {
	
	
	public static void main(String[] args) {
		//DebuggerExample.dataPrint();
		System.out.println("We are inside a main method");
		String values = DebuggerExample.datas;
		
	}
// static block of code,
// It always execute first or before main method.
// Before java 7 we can execute the code without main method , using static block.
	
	static  {
		System.out.println("We are in static block !!");
		for(int i=1; i<=10; i++) {
			System.out.println("value of i "+i);
			
		}
	}
}
