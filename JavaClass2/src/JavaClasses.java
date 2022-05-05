// consturctor, method, final, static
public class JavaClasses {
	String college ;
	final int age =20;
	// default constuctor
	public JavaClasses() {
		System.out.println("Calling default consturtor");
	}
	// constructor overloading
	public JavaClasses(String data){
		System.out.println("Calling parameter consturtor");
		college =  data;
		//age =30; // due to final it will not change
	}
	
	public void print() {
		System.out.println("Name of college : "+college+" and age : "+age);
	}
	
	public static void print(String names) {
		System.out.println("Name of Student : "+names);
	}
	
	public static void main(String[] args) {
		JavaClasses jc =  new JavaClasses("MIT PUNE");  // to create a object new keyword is always
		// call a mthod -> objectname.methodName
		jc.print();
		print("MohanReddy"); // method is called without object
		DebuggerExample deobj =  new DebuggerExample();
		//DebuggerExample.dataPrint();
		//deobj.dataPrint();
	}

}
