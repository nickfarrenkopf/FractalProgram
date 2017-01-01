package GUI;
import java.lang.reflect.Constructor;
import Fractals.Fractal;
import static Extras.Constants.*;

/**
 * Controller is the main controller for the Fractal Program. 
 * This program draws several fractals and allows you to control certain aspects of iteration.
 * 
 * NOTE: Many things were taken out due to bugs/not working. 
 * This is an ongoing project, and right now I have other things with higher priority.
 */
public class Controller {

	/**
	 * 
	 * Main method for Fractal Program.
	 * 
	 */
	public static void main(String[] args) 
	{
		@SuppressWarnings("unused")
		Controller controller = new Controller();	
	}
	/**
	 * 
	 * 
	 * 
	 */

	// GUI variables
	private Frame frame;
	private Screen screen;
	
	// Fractal variable
	private Fractal fractal;
	
	/**
	 * Initializes the controller which controls the flow of the program
	 */
	public Controller()
	{	
		frame = new Frame();
		frame.add(new TitlePage(this));
	}	
	
	///// GUI /////
	
	/**
	 * Creates the instructions page for the frame
	 */
	public void InstructionsPage()
	{
		frame.add(new InstructionsPage(this));
		frame.revalidate();
	}
	
	/**
	 * Creates the main GUI for the program given initial fractal choice
	 * @param fractalIndex
	 */
	public void makeGUI(int fractalIndex)
	{
		// Adds screen
		screen = new Screen(this);
		frame.add(screen);
		frame.revalidate();
		
		// Sets fractal
		setFractal(fractalChoices[fractalIndex]);
		screen.getSidePanel().getFractalBox().setSelectedIndex(fractalIndex);
	}

	///// FRACTAL /////

	/**
	 * Creates a new fractal object given string name of fractal
	 * @param s
	 */
	public void setFractal(String s) 
	{
		try {
			// Create new class given name and given arguments
			s = s.replaceAll("\\s","");
			Class<?> myClass = Class.forName("Fractals." + s);
			Class<?>[] types = {};

			// Creates constructor given class and arguments
			Constructor<?> constructor = myClass.getConstructor(types);
			Object[] parameters = {};

			// Creates new instance of fractal
			fractal = (Fractal) constructor.newInstance(parameters);

		} catch (Exception e) {
			e.printStackTrace();
		}
		frame.repaint();
	}	

	/**
	 * Sets initial conditions to fractal and updates screen
	 */
	public void resetFractal()
	{
		fractal.InitialConditions();
		frame.repaint();
	}
	
	/**
	 * Returns current fractal
	 * @return
	 */
	public Fractal getFractal()
	{
		return fractal;
	}

	/**
	 * Iterates fractal and updates screen
	 */
	public void Iterate() 
	{
		fractal.Iterate();
		frame.repaint();
	}

	///// GETTERS /////
	
	/**
	 * Returns the frame for access to its size
	 * @return
	 */
	public Frame getFrame()
	{
		return frame;
	}
	
	/**
	 * Returns the side panel for access to its components
	 * @return
	 */
	public SidePanel getSidePanel()
	{
		return screen.getSidePanel();
	}
}