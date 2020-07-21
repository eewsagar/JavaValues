import static java.lang.Math.cos; 
public class Example20 {


//Java code for implementing cos function 



 
//Function for calculation 

static void cal_cos(float n) { 
 float accuracy = (float) 0.0001, x1, denominator, cosx, cosval; 
 // Converting degrees to radian 
 n = n * (float) (3.142 / 180.0); 
 x1 = 1; 
 // maps the sum along the series 
 cosx = x1; 
 // holds the actual value of sin(n) 
 cosval = (float) cos(n); 
 int i = 1; 
 do { 
     denominator = 2 * i * (2 * i - 1); 
     x1 = -x1 * n * n / denominator; 
     cosx = cosx + x1; 
     i = i + 1; 
       
 } 
 while (accuracy <= cosval - cosx); 
 System.out.println(cosx); 
   
} 

//Main function 
public static void main(String[] args) { 
 float n = 30; 
 cal_cos(n); 
   
} 
} 

