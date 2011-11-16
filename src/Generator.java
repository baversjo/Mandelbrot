import java.awt.Color;
import se.lth.cs.ptdc.fractal.MandelbrotGUI;

public class Generator {
	public Generator() { }
	
	public void render(MandelbrotGUI gui) {
		gui.disableInput();
		int width = gui.getWidth();
		int height = gui.getHeight();
		Complex[][] complex = mesh(gui.getMinimumReal(), gui.getMaximumReal(),
								   gui.getMinimumImag(), gui.getMaximumImag(),
								   width, height);
		
		Color[][] picture = new Color[height][width];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				
				if (complex[y][x].getAbs2() > 1) {
					picture[y][x] = Color.white;
				} else if (complex[y][x].getRe() >= 0 && complex[y][x].getIm() >= 0) {
					picture[y][x] = Color.red;
				} else if (complex[y][x].getRe() < 0 && complex[y][x].getIm() >= 0) {
					picture[y][x] = Color.blue;
				} else if (complex[y][x].getRe() >= 0 && complex[y][x].getIm() < 0) {
					picture[y][x] = Color.green;
				} else if (complex[y][x].getRe() < 0 && complex[y][x].getIm() < 0) {
					picture[y][x] = Color.yellow;
				}
				
			}
		}
		
		gui.putData(picture, 1, 1);
		gui.enableInput();
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
