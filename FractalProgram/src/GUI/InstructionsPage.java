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
import Components.RadioGroup;
import static Extras.Constants.*;

@SuppressWarnings("serial")
public class InstructionsPage extends JPanel implements ActionListener {

	// Controller variable
	private Controller controller;
	
	// Radio Group to hold JRadioButtons
	private RadioGroup radioAll;
	
	/**
	 * Initializes an instructions page that gives user a brief description of program
	 * @param c
	 */
	public InstructionsPage(Controller c)
	{
		// Sets controller
		controller = c;

		// Adds message to page
		String[] message = {"Welcome to the Fractal Program! Here you can",
				"experiment with different fractals, changing ",
				"things like the number of iterations or initial",
				"conditions. Choose an initial fractal and have fun!"};

		// Radio buttons
		JLabel radioLabel = new JLabel("Choose initial fractal:");
		radioLabel.setFont(smallFont);
		radioAll = new RadioGroup(fractalChoices);

		// Start button
		JButton startButton = new JButton("Start");
		startButton.addActionListener(this);

		// Sets layout
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// Add things to panel
		add(Box.createVerticalGlue());
		for (String s:message)
		{
			JLabel label = new JLabel(s);
			label.setFont(mediumFont);
			label.setAlignmentX(Component.CENTER_ALIGNMENT);
			add(label);
			add(Box.createRigidArea(new Dimension(0, vertSpace)));
		}
		add(radioLabel);
		for (int i=0; i<radioAll.getSize(); i++)
		{
			add(radioAll.get(i));
			radioAll.get(i).setFont(smallFont);
			radioAll.get(i).setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		add(Box.createRigidArea(new Dimension(0, vertSpace)));
		add(startButton);
		add(Box.createVerticalGlue());
		
		// Center things on screen
		radioLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	}

	/**
	 * If action, remove this component from screen and initialize next component
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		controller.getFrame().remove(this);
		controller.makeGUI(radioAll.getSelectedIndex());
	}
}