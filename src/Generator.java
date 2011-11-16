import java.awt.Color;
import se.lth.cs.ptdc.fractal.MandelbrotGUI;

public class Generator {
	public static final int DEFAULT_ITERATIONS = 200;
	
	public Generator() { }
	
	public void render(MandelbrotGUI gui) {
		gui.disableInput();
		String extra = gui.getExtraText();
		int iterations = DEFAULT_ITERATIONS;
		if (!extra.equals("")) {
			iterations = Integer.parseInt(gui.getExtraText());
		}
		Color[] colors = new Color[iterations];
		for (int i = 0; i < colors.length; i++) {
			int f = i + 50;
			int[] c_tuple;
			switch(i % 3){
				case 0: c_tuple = new int[]{f, (int)f/2, (int)Math.sqrt(f)}; break;
				case 1: c_tuple = new int[]{(int)Math.sqrt(f),f,(int)f/2}; break;
				case 2: c_tuple = new int[]{(int)f/2,(int)Math.sqrt(f),f}; break;
				default: c_tuple = new int[]{0,0,0};
			}
			colors[colors.length-i-1] = new Color(c_tuple[0],c_tuple[1],c_tuple[2]);
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
					if(z.getAbs2() > 2){
						if (gui.getMode() == MandelbrotGUI.MODE_COLOR) {
							color = colors[k];
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
