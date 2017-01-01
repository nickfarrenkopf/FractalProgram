package GUI;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class Screen extends JPanel {

	// Controller variable
	private Controller controller;
	
	// Program panels
	private SidePanel sidePanel;	
	
	/**
	 * Screen variable draws fractal to screen and has side panel
	 * @param controller
	 */
	public Screen(Controller controller)
	{
		// Sets controller
		this.controller = controller;
		
		// Creates new panels
		JPanel screen = new JPanel();
		sidePanel = new SidePanel(controller);

		// Border for panels
		Border lineBorder = BorderFactory.createStrokeBorder(new BasicStroke(2.0f));
		screen.setBorder(lineBorder);
		sidePanel.setBorder(lineBorder);

		// Sets layout and grid variables
		GridBagLayout grid = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setLayout(grid);

		// Side Panel constraints
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.weighty = 0.01;
		add(sidePanel, c);
		
		// Screen constraints
		c.gridx = 0;
		c.weightx = 1;
		add(screen, c);
	}

	/**
	 * Draws current fractal
	 */
	public void paint(Graphics g)
	{
		super.paint(g);
		controller.getFractal().draw(g, controller.getFrame().getSize());
	}

	/**
	 * Returns side panel for access
	 * @return
	 */
	public SidePanel getSidePanel()
	{
		return sidePanel;
	}
}