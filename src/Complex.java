
public class Complex {
	private double re,im;
	public Complex(double re, double im){
		this.re = re;
		this.im = im;
	}
	public double getRe(){
		return re;
	}
	public double getIm(){
		return im;
	}

	/** Tar reda på talets absolutbelopp i kvadrat */
	public double getAbs2(){
		return Math.sqrt(re*re + im*im);
	}

	/** Adderar det komplexa talet c till detta tal */
	public void add(Complex c){
		re += c.re;
		im += c.im;
	}

	/** Multiplicerar detta tal med det komplexa talet c */
	public void mul(Complex c){
		double re = this.re,
			   im = this.im,
			   re2 = c.re,
			   im2 = c.im;
				
		this.re = re*re2 - im*im2;
		this.im = re*im2  + im*re2;
	}
}
