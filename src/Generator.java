import java.awt.Color;
import se.lth.cs.ptdc.fractal.MandelbrotGUI;

public class Generator {
	public static final int DEFAULT_ITERATIONS = 200,
							MAX_COLOR = 255;
	
	public Generator() { }
	
	public void render(MandelbrotGUI gui) {
		gui.disableInput();
		String extra = gui.getExtraText();
		int iterations = DEFAULT_ITERATIONS;
		if (!extra.equals("")) {
			iterations = Integer.parseInt(gui.getExtraText());
		}
		Color[] colors = new Color[MAX_COLOR];
		for (int i = 0; i < MAX_COLOR; i++) {
			colors[colors.length-i-1] = new Color(MAX_COLOR-i,i,0);;
		}
		
		int resolution = MandelbrotGUI.RESOLUTION_VERY_HIGH / gui.getResolution();
		int width = gui.getWidth()/resolution;
		int height = gui.getHeight()/resolution;
		Complex[][] complex = mesh(gui.getMinimumReal(), gui.getMaximumReal(),
								   gui.getMinimumImag(), gui.getMaximumImag(),
								   width, height);
		
		Color[][] picture = new Color[height][width];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Complex c = complex[y][x];
				Complex z = new Complex(0,0);
				Color color = Color.black;
				for (int k = 0; k < iterations; k++) {
					z.mul(z);
					z.add(c);
					if(z.getAbs2() > 4){
						if (gui.getMode() == MandelbrotGUI.MODE_COLOR) {
							color = colors[(MAX_COLOR/iterations)*k];
						} else {
							color = Color.white;
						}
						break;
					}
				}
				picture[y][x] = color;
			}
		}
		
		gui.putData(picture, resolution, resolution);
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
