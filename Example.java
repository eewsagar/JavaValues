public class Example{
 public static void main(String arg[]){
  System.out.print("Hello World");
  int i= 10,j=20,k=30;
   //list = new list {1,3,5,6,7,9};//this comment
   int[] list = {2, 3, 5, 7, 11, 13};
   int total = sumListOld(list);
   System.out.println("value of total:"+total);
  
   if (i>j){
	   if(i>k){
		   System.out.println("greater I : "+i);
	   }
	   else {
		   		   System.out.println("greater K: "+k);
	   }
   }
   else if (j>k){
	   		   System.out.println("greater J : "+j);
   }
   else {
		   		   System.out.println("greater K: "+k);
	   }
 }
 
 
 public static int sumListOld(int[] list)
{	int total = 0;
	for(int i = 0; i < list.length; i++)
	{	total += list[i]; // addtion
		System.out.println( list[i] );
	}
	return total;
}

}