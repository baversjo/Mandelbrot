public class Complex {
	private double re,im;

	/** Create a complex variable with real part re and imaginary part im **/
	public Complex(double re, double im){
		this.re = re;
		this.im = im;
	}
	/** Get real part **/
	public double getRe(){
		return re;
	}
	/** Get imaginary part **/
	public double getIm(){
		return im;
	}

	/** Get absolute value squared */
	public double getAbs2(){
		return re*re + im*im;
	}

	/** Add the complex number c with this complex*/
	public void add(Complex c){
		re += c.re;
		im += c.im;
	}

	/** Add the complex number c with this complex */
	public void mul(Complex c){
		double re = this.re,
			   im = this.im,
			   re2 = c.re,
			   im2 = c.im;
				
		this.re = re*re2 - im*im2;
		this.im = re*im2  + im*re2;
	}
}
