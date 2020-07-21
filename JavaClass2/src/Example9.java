
public class Example9 {
	public static void main(String[] args) {
		Complex c =  new Complex(9, 4);
		Complex d =  new Complex(4, 5);
		
		Complex e = Complex.add(c, d);
		Complex f = Complex.sub(c, d);
		Complex g = Complex.pro(c, d);
		e.printComplex();
		g.printComplex();
		f.printComplex();
		
		
		
	}

}
 class Complex{
	 int real;
	 int img;
	 
	  Complex(int r, int i){
		  real = r;
		  img =i ;
		  
	  }
	  public static Complex add(Complex a, Complex b) {
		  return new Complex((a.real+b.real), (a.img+b.img));
	  }
	  public static Complex sub(Complex a, Complex b) {
		  return new Complex((a.real-b.real), (a.img-b.img));
	  }
	  public static Complex pro(Complex a, Complex b) {
		  return new Complex(((a.real*b.real)-(a.img*b.img)),((a.real*b.real)+(a.img*b.img)));
	  }
	  public void printComplex() {
		  if (real==0 && img!=0) {
			  System.out.println("img : "+img+"i");
		  }
		  else if (real!=0 && img==0) {
			  System.out.println("real : "+real);
		  }
		  else  {
			  System.out.println("real : "+real+ "img : "+img+"i ");
		  }
	  }
 }