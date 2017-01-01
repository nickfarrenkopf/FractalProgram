package SidePanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Fractals.Fractal;
import GUI.Controller;
import static Extras.Constants.*;

@SuppressWarnings("serial")
public class IterationCard extends JPanel{

	// Controller variable
	private Controller controller;
	private Fractal fractal;
	
	// Card variables
	private JLabel currentIterations;
	private JLabel iterationTime;
	private JLabel numberPoints;
	
	/**
	 * Creates an iteration card that, for now, just displays information about fractal
	 * @param controller
	 */
	public IterationCard(Controller controller)
	{
		// Sets controller
		this.controller = controller;

		// Current iterations, iteration time, and number points Labels
		currentIterations = new JLabel("Current iterations: 0");
		iterationTime = new JLabel("Time to complete iteration: 0");
		numberPoints = new JLabel("Number of points:");
		
		// Initializes grid layout
		GridBagLayout grid = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setLayout(grid);

		// Current iterations label
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.insets = insets;
		add(currentIterations, c);

		// Iteration time label
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 1;
		c.insets = insets;
		add(iterationTime, c);
		
		// Number of points label
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 2;
		c.insets = insets;
		add(numberPoints, c);
	}

	// Updates a few numbers to screen
	public void paint()
	{
		if (controller.getFractal() != null)
		{
			currentIterations.setText("Current Iterations: " + controller.getFractal().getIterations());
			iterationTime.setText("Coming soon");
			numberPoints.setText("Number of points: " + controller.getFractal().getPointListSize());
		}
	}
	
	/**
	 * Sets default conditions of iteration card
	 */
	public void defaultConditions()
	{
		fractal = controller.getFractal();
		currentIterations.setText("Current Iterations: 0");
		iterationTime.setText("Time to complete iteration: 0");
		numberPoints.setText("Number of points: " + fractal.getPointListSize());
	}
}