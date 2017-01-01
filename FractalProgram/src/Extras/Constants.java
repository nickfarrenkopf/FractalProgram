package Extras;
import java.awt.Font;
import java.awt.Insets;

/**
 * Constants for the fractal program.
 */
public class Constants {

	
	// GUI constants
	public static final String frameTitle = "Fractal Program";
	public static final int vertSpace = 15;
	public static final Font largeFont = new Font(Font.SANS_SERIF, Font.PLAIN, 30);
	public static final Font mediumFont = new Font(Font.SANS_SERIF, Font.PLAIN, 25);
	public static final Font smallFont = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
	public static final Insets insets = new Insets(3, 6, 3, 6);
	
	
	// Fractal constants
	public static final String[] fractalChoices = {"Sierpinski Triangle", "Koch Snowflake", "Dragon Curve", 
		"Cantor Set"};
	public static final double s3 = Math.sqrt(3); 
	
	
	// Plotting constants
	public static final double scale = 0.80;
	public static final double buffer = 40;
	public static final double minTotal = 1.5;
	public static final double minSide = 0.5;
	public static final String decimalLength = "%.3f";

	
	// Timer constants
	public static final long warningTime = 3;
	public static final long maxTime = 15;
	public static final int timerTime = 3000;
	

}