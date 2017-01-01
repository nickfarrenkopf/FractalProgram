package Fractals;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Random;
import Extras.Point;
import static Extras.Constants.*;

@SuppressWarnings("serial")
public class KochSnowflake extends Fractal{
	
	// Height of triangle added to fractal
	private boolean random;
	
	/**
	 * Initializes von Koch snowflake
	 */
	public KochSnowflake()
	{
		// Sets controller and initializes fractal
		InitialConditions();
		
		// Constant Initial Conditions
		iterations = 0;
		random = false;
		maxIterations = 7;
	}
	
	/**
	 * Sets initial conditions for snowflake
	 */
	public void InitialConditions()
	{		
		// Calculated initial conditions
		pointList = new ArrayList<>();
		pointList.add(new Point(0, 0.75));
		pointList.add(new Point(1 / s3 * 0.75, 0));
		pointList.add(new Point(2 / s3 * 0.75, 0.75));
		pointList.add(pointList.get(0));
	}
	
	/**
	 * Sets random conditions for snowflake
	 */
	public void RandomConditions()
	{
		// Variables for easy read and random
		Point p0 = new Point();
		Point p1 = new Point();
		Point p2 = new Point();
		double distance = 0;

		// Checks that distance between all points is at least value
		while (distance < minTotal)
		{
			p0 = p0.randomPoint();
			p1 = p1.randomPoint();
			p2 = p2.randomPoint();
			distance = p0.distance(p1) + p1.distance(p2) + p2.distance(p0);
			if (p0.distance(p1) < minSide || p1.distance(p2) < minSide || p2.distance(p0) < minSide)
				distance = 0;
		}

		// Adds to point list
		pointList = new ArrayList<>();
		pointList.add(p0);
		pointList.add(p1);
		pointList.add(p2);
		pointList.add(p0);
	}


	/**
	 * Iterates snowflake by adding points in between two points, then adds another based on height
	 * value for how far iteration is from starting point
	 */
	public void Iterate()
	{
		// Variables
		double xnew, ynew;
		double xdist, ydist;
		double height;
		double theta;
		double pi = Math.PI;
		Point p0, p1;
		Random rand = new Random();

		// Adds opposite, iterating backwards over current point list
		for (int j=pointList.size() - 2; j>=0; j--)
		{
			// Current points
			p0 = pointList.get(j);
			p1 = pointList.get(j + 1);
			
			// Get distance between next point and current point
			xdist = p1.getX() - p0.getX();
			ydist = p1.getY() - p0.getY();
			
			// Height of new triangle
			height = Math.sqrt(xdist * xdist + ydist * ydist) * Math.sqrt(3) / 6;
			if (random)
				height = (rand.nextDouble() + 0.5) * height;

			// Calculates theta between points
			theta = Math.atan(ydist / xdist);
			if (p1.getX() < p0.getX())
				theta += pi;

			// Adds 1st point
			xnew = p0.getX() + xdist * 2 / 3;
			ynew = p0.getY() + ydist * 2 / 3;
			pointList.add(j + 1, new Point(xnew, ynew));

			// Adds 2nd point
			xnew = p0.getX() + xdist / 2 - height * Math.cos(theta + pi / 2);
			ynew = p0.getY() + ydist / 2 - height * Math.sin(theta + pi / 2);
			pointList.add(j + 1, new Point(xnew, ynew));

			// Adds 3rd point
			xnew = p0.getX() + xdist / 3;
			ynew = p0.getY() + ydist / 3;
			pointList.add(j + 1, new Point(xnew, ynew));
		}

		// Update values
		iterations++;
	}
	
	/**
	 * Draws fractal given graphics g and frame size
	 */
	public void draw(Graphics g, Dimension size)
	{
		// Finds scale to fit fractal to screen
		double s = (size.getHeight() > size.getWidth()? size.getWidth() * scale: size.getHeight() * scale);

		// Iterates through point list, connecting lines
		Graphics2D g2 = (Graphics2D) g;
		double p0x, p0y, p1x, p1y;
		for(int i=0; i<pointList.size() - 1; i++)
		{
			// Grab point values
			p0x = pointList.get(i).getX() * s + buffer;
			p0y = pointList.get(i).getY() * s + buffer;
			p1x = pointList.get(i + 1).getX() * s + buffer;
			p1y = pointList.get(i + 1).getY() * s + buffer;
			
			// Connect points with a line
			g2.draw(new Line2D.Double(p0x, p0y, p1x, p1y));
		}	
	}
}