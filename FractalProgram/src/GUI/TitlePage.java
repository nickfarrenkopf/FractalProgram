package GUI;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static Extras.Constants.*;

@SuppressWarnings("serial")
class TitlePage extends JPanel implements ActionListener
{
	// Controller variable
	private Controller controller;

	/**
	 * Initialize title screen with a few messages and components
	 * @param c
	 */
	public TitlePage(Controller c)
	{
		// Sets controller
		controller = c;
		
		// Sets text of labels and buttons
		JLabel titleLabel = new JLabel("Fractal Program");
		JLabel nameLabel = new JLabel("by Nick Farrenkopf");
		
		// Start button
		JButton startButton = new JButton("Start");
		startButton.addActionListener(this);
		
		// Set fonts for labels and buttons
		titleLabel.setFont(largeFont);
		nameLabel.setFont(mediumFont);
		startButton.setFont(smallFont);

		// Sets layout
		BoxLayout box = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(box);

		// Adds labels and buttons, and glue/rigid area for esthetic appeal
		add(Box.createVerticalGlue());
		add(titleLabel);
		add(Box.createRigidArea(new Dimension(0, vertSpace)));
		add(nameLabel);
		add(Box.createRigidArea(new Dimension(0, vertSpace * 2)));
		add(startButton);
		add(Box.createVerticalGlue());
		
		// Sets alignment on screen
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	}

	/**
	 * If action, remove this component from screen and initialize next component
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		controller.getFrame().remove(this);
		controller.InstructionsPage();
	}
}