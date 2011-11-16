import se.lth.cs.ptdc.fractal.MandelbrotGUI;

public class Mandelbrot {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean rendered = false;
		MandelbrotGUI gui = new MandelbrotGUI();
		Generator g = new Generator();
		while (true) {
			switch (gui.getCommand()) {
				case MandelbrotGUI.RENDER:
					gui.clearPlane();
					g.render(gui);
					rendered = true;
					break;
				case MandelbrotGUI.ZOOM:
					if (rendered) {
						g.render(gui);
					}
					break;
				case MandelbrotGUI.RESET:
					gui.resetPlane();
					gui.clearPlane();
					rendered = false;
					break;
				case MandelbrotGUI.QUIT:
					System.exit(0);
					break;
				default:
					System.err.println("Command not yet implented: NR(" + gui.getCommand() + ")");
					break;
			}			
		}
	} 

}
