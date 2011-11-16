import se.lth.cs.ptdc.fractal.MandelbrotGUI;

public class Generator {
	public Generator() { }
	
	public void render(MandelbrotGUI gui) {
		
	}
	
	private Complex[][] mesh(double minRe, double maxRe, double minIm, double maxIm, int width, int height) {
		Complex[][] complex = new Complex[height][width];
		double rePart = (maxRe - minRe) / width;
		double imPart = (maxIm - minIm) / height;
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				complex[y][x] = new Complex(minRe + rePart * x, maxIm - imPart * y);
			}
		}
		return complex;
	}
}
