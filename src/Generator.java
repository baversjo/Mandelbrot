import java.awt.Color;
import se.lth.cs.ptdc.fractal.MandelbrotGUI;

/** Render a mandlebrot in the imi**/
public class Generator {
	public static final int DEFAULT_ITERATIONS = 200,
							MAX_COLOR = 255,
							LIMIT_SQUARED = (int) Math.pow(2,2);
	
	//Generate a color spectrum. 
	//Do this in a static block to avoid regenerating the color set every object initiation.
	public static final Color[] COLORS;
	static{
		Color[] colors = new Color[MAX_COLOR];
		for (int i = 0; i < MAX_COLOR; i++) {
			colors[colors.length-i-1] = new Color(MAX_COLOR-i,i,0);
		}
		COLORS = colors;
	}
	
	public void render(MandelbrotGUI gui) {
		//disable GUI input during rendering (might take a while)
		gui.disableInput();
		
		//Get number of iterations from extra parameter field if available
		//Default is DEFAULT_ITERATIONS
		String extra = gui.getExtraText();
		int iterations = DEFAULT_ITERATIONS;
		if (!extra.equals("")) {
			iterations = Integer.parseInt(gui.getExtraText());
		}
		
		//calculate resolution ratio based on user preference in GUI 
		int resolution = MandelbrotGUI.RESOLUTION_VERY_HIGH / gui.getResolution();
		
		//with this ratio, calculate grid size. 
		//While the number of pixels on the grid are the same regardless of resolution, the number of points
		//we plot on should vary
		int width = gui.getWidth()/resolution;
		int height = gui.getHeight()/resolution;
		
		//get complex talplan
		Complex[][] complex = mesh(gui.getMinimumReal(), gui.getMaximumReal(),
								   gui.getMinimumImag(), gui.getMaximumImag(),
								   width, height);
		
		//Render the actual picture (matrix with color objects)
		Color[][] picture = new Color[height][width];
		//Go through all complex number (left to right, top to bottom)
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Complex c = complex[y][x];
				Color color = Color.black;
				
				/*	Run the mandelbrot algorithm with a maximum of "iterations" times and set color.
					If the complex is within the mandelbrot set, the color is black.
					Else the color is based on the how fast the complex diverge (if color mode is enabled).
					If color mode is disabled, complex numbers not within the set will be white.
				*/
				Complex z = new Complex(0,0);
				for (int k = 0; k < iterations; k++) {
					z.mul(z);
					z.add(c);
					if(z.getAbs2() > LIMIT_SQUARED){
						if (gui.getMode() == MandelbrotGUI.MODE_COLOR) {
							//COLORS is MAX_COLOR long. Because the number of iterations can vary,
							// scale k to fit in the color set.
							color = COLORS[(MAX_COLOR/iterations)*k];
						} else {
							color = Color.white;
						}
						break;
					}
				}
				picture[y][x] = color;
			}
		}
		//insert the data to the GUI and release input lock.
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
