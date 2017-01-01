package SidePanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import GUI.Controller;
import static Extras.Constants.insets;

@SuppressWarnings("serial")
public class ButtonBox extends JPanel implements ActionListener {

	// Controller variable
	private Controller controller;
	
	// Buttons
	private JButton iterateButton;
	private JButton resetButton;
	private JButton exitButton;
	
	/**
	 *  Creates a ButtonBox object that houses RESET and EXIT button and controls what they do
	 * @param controller
	 */
	public ButtonBox(Controller controller)
	{	
		// Initializes frame variable
		this.controller = controller;
		
		// Set button text and position
		iterateButton = new JButton("Iterate");
		resetButton = new JButton("Reset");
		exitButton = new JButton("Exit");
		
		// Adds listener for button clicks
		iterateButton.addActionListener(this);
		resetButton.addActionListener(this);
		exitButton.addActionListener(this);

		// Sets grid bag constraints and adds to panel (layout)
		GridBagLayout grid = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setLayout(grid);

		// Iterate Button
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		c.insets = insets;
		add(iterateButton,c);

		// Reset Button
		c.gridy = 1;
		add(resetButton,c);

		// Exit Button
		c.gridy = 2;
		add(exitButton,c);
	}
	
	/**
	 * Action listener to decide what buttons do when clicked
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// Iterate button click
		if (e.getSource() == iterateButton)
			controller.Iterate();
		
		// Reset button click
		if (e.getSource() == resetButton)
			controller.resetFractal();
		
		// Exit button click
		if (e.getSource() == exitButton)
		{
			// Double checks wanting to exit program
			String exitMessage = "Are you sure you want to exit?";
			int result = JOptionPane.showConfirmDialog (null, exitMessage,"Warning", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (result == JOptionPane.YES_OPTION)
			{
				controller.getFrame().Exit();
			}
		}
	}
}