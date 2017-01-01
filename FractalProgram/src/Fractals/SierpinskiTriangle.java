package Fractals;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import Extras.Point;
import static Extras.Constants.*;

@SuppressWarnings("serial")
public class SierpinskiTriangle extends Fractal{
	
	// Array List for next iteration
	private ArrayList<Point> nextIter = new ArrayList<>();
	
	/**
	 * Initializes 
	 */
	public SierpinskiTriangle()
	{
		// Sets controller and initializes fractal
		InitialConditions();
		
		// Limiting conditions
		maxIterations = 13;
	}
	
	/**
	 * Sets initial conditions for sierpinski triangle
	 */
	public void InitialConditions()
	{		
		// Point list reset
		pointList = new ArrayList<>();
		pointList.add(new Point(0, 1));
		pointList.add(new Point(1 / s3, 0));
		pointList.add(new Point(2 / s3, 1));

		// Constant initial conditions
		iterations = 0;
		nextIter = new ArrayList<>();
		nextIter.addAll(pointList);
	}

	/**
	 * Sets random conditions for sierpinski triangle
	 */
	public void RandomConditions()
	{
		// Variables for easy read and random
		Point p0 = new Point();
		Point p1 = new Point();
		Point p2 = new Point();
		double distance = 0;

		// Checks that distance between all points is above minimum
		while (distance < minTotal)
		{
			p0 = p0.randomPoint();
			p1 = p1.randomPoint();
			p2 = p2.randomPoint();
			distance = p0.distance(p1) + p1.distance(p2) + p2.distance(p0);
			if (p0.distance(p1) < minSide || (p1.distance(p2) < minSide || p2.distance(p0) < minSide))
				distance = 0;
		}

		// Adds to point list
		pointList = new ArrayList<>();
		pointList.add(p0);
		pointList.add(p1);
		pointList.add(p2);

		// Constant initial conditions
		iterations = 0;
		nextIter = new ArrayList<Point>();
		nextIter.addAll(pointList);
	}

	/**
	 * Iterates triangle by adding new triangle inside of existing triangle
	 */
	public void Iterate()
	{
		// Variables for iteration and ease of read
		ArrayList<Point> newIter = new ArrayList<>();
		Point p0, p1, p2;
		Point mid01, mid12, mid20;

		// Adds to point list of actual points and sets what to do for the next iteration
		for(int i=0; i<Math.pow(3, iterations); i++)
		{
			// Grabs points and midpoints for adding
			p0 = nextIter.get(3 * i);
			p1 = nextIter.get(3 * i + 1);
			p2 = nextIter.get(3 * i + 2);
			mid01 = p0.getMidPoint(p1);
			mid12 = p1.getMidPoint(p2);
			mid20 = p2.getMidPoint(p0);

			// Adds sets of triangles for next iteration
			newIter.add(p0);
			newIter.add(mid01);
			newIter.add(mid20);

			newIter.add(p1);
			newIter.add(mid12);
			newIter.add(mid01);

			newIter.add(p2);
			newIter.add(mid12);
			newIter.add(mid20);
		}

		// Update next iteration and point list
		nextIter.clear();
		nextIter.addAll(newIter);
		pointList.addAll(newIter);
		
		// Increase iterations counter
		iterations++;
	}
	
	/**
	 * Draws fractal given graphics g and frame size
	 */
	public void draw(Graphics g, Dimension size)
	{
		// Finds scale to fit fractal to screen
		double s = (size.getHeight() > size.getWidth()? size.getWidth() * scale: size.getHeight() * scale);
		
		// Iterates through groups of 3 points, connecting with lines
		Graphics2D g2 = (Graphics2D) g;
		double p0x, p0y, p1x, p1y, p2x, p2y;
		for (int i=0; i<pointList.size() / 3; i++)
		{
			// Grabs point values
			p0x = pointList.get(3 * i).getX() * s + buffer;
			p0y = pointList.get(3 * i).getY() * s + buffer;
			p1x = pointList.get(3 * i + 1).getX() * s + buffer;
			p1y = pointList.get(3 * i + 1).getY() * s + buffer;
			p2x = pointList.get(3 * i + 2).getX() * s + buffer;
			p2y = pointList.get(3 * i + 2).getY() * s + buffer;

			// Draws triangle
			g2.draw(new Line2D.Double(p0x, p0y, p1x, p1y));
			g2.draw(new Line2D.Double(p1x, p1y, p2x, p2y));
			g2.draw(new Line2D.Double(p2x, p2y, p0x, p0y));
		}
	}
}