import java.util.Scanner;
public class Example9 {
	

	
	    public static void main(String[] args)
	    {
	        Scanner console = new Scanner(System.in);
	     
	        int dividend, divisor;
	        int remainder, hcf = 0;
	        
	        System.out.print("Enter the first number ");
	        dividend = console.nextInt();
	        
	        System.out.print("Enter the second number ");
	        divisor = console.nextInt();        
	        
	        do
		{
	            remainder = dividend % divisor;
	            
	            if(remainder == 0)
	            {
	                hcf = divisor;
	            }
		    else
	            {
	                dividend = divisor;
	                divisor = remainder;
		    }
	            
	        }while(remainder != 0);

	        System.out.println("HCF: " + hcf);
	    }  
	}
