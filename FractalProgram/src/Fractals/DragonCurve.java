package Fractals;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import static Extras.Constants.*;

/**
 * Dragon Curve is a unique fractal based on folding patterns.
 * 
 * NOTE: Code is not clean. I have other things I would like to work on, so I got the code working, not pretty. 
 */
@SuppressWarnings("serial")
public class DragonCurve extends Fractal {

	// Creates different point List
	private ArrayList<Integer> pointList; 
	
	// Relative size variables so can fit to screen
	private int[] xRange = new int[2];
	private int[] yRange = new int[2];
	double newScale;

	/**
	 * Initiailizes Dragon Curve with initial conditions and sets max number of iterations
	 */
	public DragonCurve()
	{
		// Set controller and initializes
		InitialConditions();
		
		// Limiting condition
		maxIterations = 20;
	}
	
	/**
	 * Initial conditions for Dragon Curve. I know I can do the Range thing less awkwardly.
	 * Another project for another time.
	 */
	public void InitialConditions()
	{
		// Initializes point list
		pointList = new ArrayList<>();
		pointList.add(1);
		
		// Other initial values
		iterations = 0;
		xRange[0] = 0;
		xRange[1] = 0;
		yRange[0] = 0;
		yRange[1] = 0;
		
		// First iteration
		Iterate();
	}
	
	/**
	 * Iterates dragon curve.
	 */
	public void Iterate()
	{
		// Add one
		pointList.add(1);

		// Adds opposite, iterating backwards over current point list
		for (int j=pointList.size() - 1; j>0; j--)
			if (pointList.get(j - 1) == 0)
				pointList.add(1);
			else
				pointList.add(0);

		// Update values
		iterations++;
		updateRelativeSize();
		
		// Repaint screen
		repaint();
	}
	
	/**
	 * Updates relative size for pretty graphing
	 */
	public void updateRelativeSize()
	{
		// Initial variables (starts at (0, 0), but moves 1 in positive direction)
		int x = 1;
		int y = 0;
		int direction = 0;
		
		// Changes direction according to point list, then moves relative point 
		for (Integer i:pointList)
		{	
			// Changes direction based on new point
			direction = updateDirection(direction, i);
		
			// Moves point
			switch (direction)
			{
			case 0:	x++; break;
			case 1: y++; break;
			case 2: x--; break;
			case 3: y--; break;
			}
			
			// Checks if min or max has been beaten
			checkRange(x, y);
		}
	}
	
	/**
	 * Direction based on current direction and integer
	 * @param direction
	 * @param i
	 * @return
	 */
	public int updateDirection(int direction, int i)
	{
		// Changes direction based on new point
		if (i == 1)
			direction ++;
		else
			direction --;

		// Updates out of bounds direction
		if (direction == 4)
			direction = 0;
		if (direction == -1)
			direction = 3;
		
		return direction;
	}
	
	/**
	 * Checks if min and max have been beaten
	 * @param x
	 * @param y
	 */
	public void checkRange(int x, int y)
	{
		if (x < xRange[0])
			xRange[0] = x;
		if (x > xRange[1])
			xRange[1] = x;
		if (y < yRange[0])
			yRange[0] = y;
		if (y > yRange[1])
			yRange[1] = y;
	}

	/**
	 * Different point list, so need new getter
	 */
	public int getPointListSize()
	{
		return this.pointList.size();
	}
	
	/**
	 * Draws dragon curve by turning left on 0, right on 1
	 */
	public void draw(Graphics g, Dimension size)
	{	
		// Initial functions and variables
		Graphics2D g2 = (Graphics2D) g;
		
		// Look at full range for x and y
		double xFull = xRange[1] - xRange[0];
		double yFull = yRange[1] - yRange[0];
		double xBuffer = xFull * 0.05;
		double yBuffer = yFull * 0.05;
		
		// Set minimum buffer
		if (xBuffer < 2)
			xBuffer = 2;
		if (yBuffer < 2)
			yBuffer = 2;
		
		// Add buffer so looks nicer on screen
		xFull = xFull + xBuffer;
		yFull = yFull + yBuffer;
		
		// Calculate line length based on size of fractal
		double delta;
		double screenHeight = size.getHeight() * scale;
		double screenWidth = size.getWidth() * scale;
		if (screenWidth / xFull > screenHeight / yFull)
			delta = screenHeight / yFull;
		else
			delta = screenWidth / xFull;
		
		// Starting coordinates, starting near center of screen
		double x = (xBuffer / 2 - xRange[0]) / xFull * screenWidth; 
		double y = (1 -(yBuffer / 2 - yRange[0]) / yFull) * screenHeight;
		
		// Variables to help calculate new points
		double xnew;
		double ynew;
		double piHalf = Math.PI / 2;
		int direction = 0;
		
		// Calculates next point and draws line connecting two points
		for(Integer i:pointList)
		{
			// Creates new point and draws line
			xnew = x + Math.cos(direction * piHalf) * delta;
			ynew = y - Math.sin(direction * piHalf) * delta;
			g2.draw(new Line2D.Double(x, y, xnew, ynew));
			
			// Updates direction, x, and y
			direction = updateDirection(direction, i);
			x = xnew;
			y = ynew;
		}
		
		// Last iteration
		xnew = x + Math.cos(direction * piHalf) * delta;
		ynew = y - Math.sin(direction * piHalf) * delta;
		g2.draw(new Line2D.Double(x, y, xnew, ynew));
	}
}