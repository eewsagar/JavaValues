
public class DebuggerExample {
	// default constructor
	 DebuggerExample(){}
	 static String datas = "RAM";
	 public static void main(String[] args) {
		for(int i=1; i<=10; i++) {
			System.out.println("value of i "+i);
			
		}
		dataPrint();
	}
	 private static void dataPrint() {
		 System.out.println("We the people of india, Proud to be an indian !!!");
		 System.out.println("We the people of india, Proud to be an indian !!!" +datas);
	 }

}