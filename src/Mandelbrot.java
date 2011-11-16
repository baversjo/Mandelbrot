import se.lth.cs.ptdc.fractal.MandelbrotGUI;

public class Mandelbrot {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MandelbrotGUI gui = new MandelbrotGUI();
		while (true) {
			switch (gui.getCommand()) {
				case MandelbrotGUI.RENDER:
					break;
				case MandelbrotGUI.ZOOM:
					break;
				case MandelbrotGUI.RESET:
					break;
				case MandelbrotGUI.QUIT:
					break;
				default:
					System.err.println("Command not yet implented: NR(" + gui.getCommand() + ")");
					break;
			}
			
		}
	}

}
