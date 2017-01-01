package GUI;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import SidePanel.ButtonBox;
import SidePanel.IterationCard;
import static Extras.Constants.fractalChoices;

// Creates the side panel that holds all of the programs main functions, i.e.
// choosing initial vertices, iteration constants, and point color
@SuppressWarnings("serial")
public class SidePanel extends JPanel implements ActionListener{

	// Controller variable
	Controller controller;
	
	// Choosing the fractal
	private JComboBox<String> fChoiceBox;
	
	// Card variables
	private JTabbedPane tabbed;
	private IterationCard ic;
	
	// Box variables
	private ButtonBox buttonBox;
	
	/**
	 * Side panel has a few components for iterating and reseting and exiting
	 * @param controller
	 */
	public SidePanel(Controller controller)
	{	
		// Sets controller
		this.controller = controller;
		
		// Fractal choice section
		JLabel fChoiceLabel = new JLabel("Choose Fractal:");
		fChoiceBox = new JComboBox<>(fractalChoices);
		fChoiceBox.addActionListener(this);
		
		// Adds fractal choice panel
		JPanel choicePanel = new JPanel();
		choicePanel.add(fChoiceLabel);
		choicePanel.add(fChoiceBox);
		
		// Card pane
		tabbed = new JTabbedPane();
		ic = new IterationCard(controller);
		tabbed.add("Iterations", ic);
		
		// Creates button box
		buttonBox = new ButtonBox(controller);

		// Initializes grid layout
		GridBagLayout grid = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setLayout(grid);
		
		// Adds choice panel
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weighty = 1;
		add(choicePanel, c);
		
		// Adds tabbed pane
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 1;
		c.weighty = 5;
		add(tabbed, c);
		
		// Adds button box
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 2;
		c.weighty = 1;
		add(buttonBox, c);
	}
	
	/**
	 * Id user changes fractal, update on controller
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == fChoiceBox)
			controller.setFractal((String) fChoiceBox.getSelectedItem());
	}
	
	/**
	 * Getter for initialization of screen
	 * @return
	 */
	public JComboBox<String> getFractalBox()
	{
		return fChoiceBox;
	}
}