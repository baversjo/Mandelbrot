import se.lth.cs.ptdc.fractal.MandelbrotGUI;

public class Mandelbrot {
	public static void main(String[] args) {
		boolean rendered = false;
		MandelbrotGUI gui = new MandelbrotGUI();
		Generator g = new Generator();
		while (true) {
			//wait for GUI command
			switch (gui.getCommand()) {
				case MandelbrotGUI.RENDER:
					//render mandelbrot
					g.render(gui);
					rendered = true;
					break;
				case MandelbrotGUI.ZOOM:
					//only allow if already rendered
					if (rendered) {
						g.render(gui);
					}
					break;
				case MandelbrotGUI.RESET:
					//reset everything to initial state
					gui.resetPlane();
					gui.clearPlane();
					rendered = false;
					break;
				case MandelbrotGUI.QUIT:
					//quit application
					System.exit(0);
					break;
				default:
					//STDERR if GUI command not yet implemented
					System.err.println("Command not yet implented: NR(" + gui.getCommand() + ")");
					break;
			}			
		}
	} 

}
