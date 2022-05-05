// plain old java object
public class TestPojoClass {
	public static void main(String args[]) {
	StudentInfo stuObj =  new StudentInfo();
	stuObj.setRoll_no(1000);
	stuObj.setStuName("Rahul");
	
	System.out.println("value of student : "
	+stuObj.getRoll_no()+" "+stuObj.getStuName());
	}
}
