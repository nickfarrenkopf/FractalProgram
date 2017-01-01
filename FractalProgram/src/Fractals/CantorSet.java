package Fractals;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import static Extras.Constants.*;

/**
 * Creates the Cantor Set. Shows 5 versions of the cantor set. The first three vary on shape used and showing everything
 * in one line. The last two draw each iteration side by side and vary by shape used.
 */
@SuppressWarnings("serial")
public class CantorSet extends Fractal {

	// Creates new point List for doubles instead of points
	private ArrayList<Double> pointList;
	private ArrayList<Double> sideSet;

	/**
	 * Initializes the Cantor Set with initial conditions
	 */
	public CantorSet()
	{
		// Sets controller and initializes fractal
		InitialConditions();
	}
	
	/**
	 * Sets initial conditions for the Cantor Set, setting pointList and sideSet to same start
	 */
	public void InitialConditions()
	{
		// Initializes point list
		pointList = new ArrayList<>();
		pointList.add(0.);
		pointList.add(1.);
		
		// Other point list
		sideSet = new ArrayList<>();
		sideSet.addAll(pointList);
		
		// Other initial variables
		iterations = 0;
		maxIterations = 10;
	}
	
	/**
	 * Iterates the Cantor Set.
	 * Point list iterations consist of adding two points in between existing sets of two points.
	 * Side set iteration consists of adding the new Cantor set to the end of each existing Cantor set
	 */
	public void Iterate()
	{
		// Random variables for easy reading
		double p0, p1;
		double p2, p3;
		int start;

		// Iterates last iteration and adds points to end of list
		for (int i=pointList.size() - 2; i>=0; i -=2)
		{
			// Grabs point values
			p0 = pointList.get(i);
			p1 = pointList.get(i + 1);
			p2 = p0 * 2 / 3 + p1 / 3;
			p3 = p0 / 3 + p1 * 2 / 3;
			
			// Adds to point list
			pointList.add(i + 1, p3);
			pointList.add(i + 1, p2);
		}
		
		// Iterates side set
		start = sideSet.size() - (int) Math.pow(2, iterations + 1);
		for (int i=0; i<(int) Math.pow(2,  iterations); i++)
		{
			// Grabs point values
			p0 = sideSet.get(start + 2 * i);
			p1 = sideSet.get(start + 2 * i + 1);
			p2 = p0 * 2 / 3 + p1 / 3;
			p3 = p0 / 3 + p1 * 2 / 3;
			
			// Adds point to side set
			sideSet.add(p0);
			sideSet.add(p2);
			sideSet.add(p3);
			sideSet.add(p1);
		}

		// Updates other variables
		iterations++;
	}
	
	/**
	 * Point list is different point list, so need getter here
	 */
	public int getPointListSize()
	{
		return pointList.size();
	}
	
	/**
	 * Draws 5 Cantor sets side by side.
	 * Cantor 1 - Everything in one line using points
	 * Cantor 2 - Everything in one line using lines
	 * Cantor 3 - Everything in one line using rectangles for added depth
	 * Cantor 4 - Iterations side by side using lines
	 * Cantor 5 - Iterations side by side using rectangles for added depth
	 */
	public void draw(Graphics g, Dimension size)
	{
		// Initial functions and variables
		Graphics2D g2 = (Graphics2D) g;
		
		// Variables for easy code read
		double p0, p1;
		double rectSize = 4;

		// Scale to fit to screen
		double w = size.getWidth() * scale * scale;
		double h = size.getHeight() * scale;
		double[] height = new double[5];
		for (int i=1; i<6; i++)
			height[i - 1] = h * i / 6;

		// Draws the cantor set
		for (int i=0; i<pointList.size(); i += 2)
		{
			// Grabs point values
			p0 = pointList.get(i) * w + buffer;
			p1 = pointList.get(i + 1) * w + buffer;

			// Draw points
			g2.draw(new Ellipse2D.Double(p0, height[0], rectSize, rectSize));
			g2.draw(new Ellipse2D.Double(p1, height[0], rectSize, rectSize));
			
			// Draws lines
			g2.draw(new Line2D.Double(p0, height[1], p1, height[1]));

			// Draws rectangles
			g2.fill(new Rectangle2D.Double(p0, height[2], (p1 - p0), rectSize));
		}

		// Draws side set
		int iter = 0;
		int counter = 0;
		for (int i=0; i<sideSet.size(); i += 2)
		{
			// Grabs point values for side set
			p0 = sideSet.get(i) * w + buffer;
			p1 = sideSet.get(i + 1) * w + buffer;

			// Draws lines for side set
			g2.draw(new Line2D.Double(p0, height[3] + rectSize * iter, p1, height[3] + rectSize * iter));

			// Draws rectangles for side set
			g2.fill(new Rectangle2D.Double(p0, height[4] + rectSize * iter, (p1 - p0), rectSize));
			
			// Increase iteration point counter
			counter += 2;
			if (counter >= Math.pow(2, iter + 1))
			{
				counter = 0;
				iter++;
			}
		}
	}	
}