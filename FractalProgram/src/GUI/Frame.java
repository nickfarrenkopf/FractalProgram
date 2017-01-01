package GUI;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import static Extras.Constants.*;

@SuppressWarnings("serial")
public class Frame extends JFrame {			
	
	/**
	 * Creates a frame GUI for the fratal program
	 */
	public Frame()
	{	
		// Sets frame properties
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(frameTitle);
		setVisible(true);
		
		// Size of pop up window, centering on computer screen
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension dim = kit.getScreenSize();
		setSize(dim.width / 2, dim.height / 2 + 50);
		setLocation(dim.width / 4, dim.height / 4);	
	}
	
	/**
	 * Exits program if button ever pressed
	 */
	public void Exit()
	{
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}