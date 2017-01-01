package Fractals;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JComponent;
import Extras.Point;

/**
 * Fractal is blank fractal with parts that all fractals have.
 */
@SuppressWarnings("serial")
public class Fractal extends JComponent {

	// Array list to hold points
	protected ArrayList<Point> pointList;
	
	// Iteration variables
	protected int iterations;
	protected int maxIterations = 5;

	/**
	 * All fractals must have initial conditions, random conditions, iterate, and draw
	 */
	public void InitialConditions() {}
	public void RandomConditions() {}
	public void Iterate() {}
	public void draw(Graphics g, Dimension frameSize) {}

	/**
	 * Returns the size of the point list for iterating over
	 * @return int - Size of point list
	 */
	public int getPointListSize()
	{
		return pointList.size();
	}
	
	/**
	 * Returns the current number of iterations
	 * @return int - Number of iterations
	 */
	public int getIterations()
	{
		return iterations;
	}
	
	/**
	 * Returns the maximum number of iterations so program doesn't crash
	 * @return int - Max number of iterations
	 */
	public int getMaxIterations()
	{
		return maxIterations;
	}
}