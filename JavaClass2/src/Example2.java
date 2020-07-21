public class Example2 {
	public static void main(String[] args) {
		Student4 s1 =  new Student4();
		Student4 s2 =  new Student4();
		s1.name="Sam";
		s1.roll_no=1;
		s1.phone_no=778565443;
		s1.address="India,Maharastra,Mumbai";
		
		
		s2.name="John";
		s2.roll_no=2;
		s2.phone_no=878565443;
		s2.address="India,Maharastra,Nashik";
		System.out.println("Student name is : "+s1.name +"\nrollnumber is: "
		+s1.roll_no+"\nPhone number is: "+s1.phone_no+"\nAddress is: "+s1.address);
		System.out.println("");
		System.out.println("*************************");
		System.out.println("Student name is : "+s2.name +"\nrollnumber is: "
				+s2.roll_no+"\nPhone number is: "+s2.phone_no+"\nAddress is: "+s2.address);
		
	}

}
 class Student4{
	 int roll_no;
	 String name;
	 long phone_no;
	 String address;
	 
 }