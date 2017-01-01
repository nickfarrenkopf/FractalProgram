package Extras;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Point is a Point2D.Double that adds a randomized point and midpoint option.
 */
@SuppressWarnings("serial")
public class Point extends Point2D.Double
{
	/**
	 * Creates a blank point
	 */
	public Point() {}
	
	/**
	 * Initializes point with x and y coordinates
	 * @param x - double x coordinate
	 * @param y - double y coordinate
	 */
	public Point(double x, double y)
	{
		setLocation(x, y);
	}

	/**
	 * Creates a random point somewhere between (0,0)x(1,1)
	 * @return Point 
	 */
	public Point randomPoint()
	{
		Random rand = new Random();
		double x = rand.nextDouble();
		double y = rand.nextDouble();
		return new Point(x, y);
	}

	/**
	 * Returns a point that is the midpoint between this and given point
	 * @param p - Point 
	 * @return Point - midpoint between this and given point
	 */
	public Point getMidPoint(Point p)
	{
		double xmid = (getX() + p.getX()) / 2;
		double ymid = (getY() + p.getY()) / 2;
		return new Point(xmid, ymid);
	}
}