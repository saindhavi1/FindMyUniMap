package view;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.FontClass;

//This class represents the frame that will appear when the user wants
//to select a university to display on the map
public class SelectUniversity extends JFrame implements ActionListener {

	FontClass fonts = new FontClass();
	JLabel instructionsLabel = new JLabel("Select the universities you would like to see on the map:");
	JCheckBox[] uniCheckBoxes = new JCheckBox[Map.universityArray.length];
	JButton submitButton = new JButton("Submit");
	Color buttonColor = new Color(57, 90, 154);

	public SelectUniversity() {
		//create the select university frame
		setSize(800, 470);
		setTitle("Select University");
		setVisible(true);
		setLayout(null);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(217, 217, 217));

		//add the instructions
		add(instructionsLabel);
		instructionsLabel.setFont(FontClass.medium25);
		instructionsLabel.setBounds(30, 15, 800, 25);

		//create the checkboxes on the frame
		for (int uni = 0; uni < Map.universityArray.length; uni++) {
			uniCheckBoxes[uni] = new JCheckBox(Map.universityArray[uni].getName());
			uniCheckBoxes[uni].setFont(FontClass.regular16);
			add(uniCheckBoxes[uni]);
			//if 8 university's checkboxes have been added
			//then start adding the remaining checkboxes to the right of them
			//instead of adding them to the bottom
			if (uni >= 9) {
				uniCheckBoxes[uni].setBounds(410, 45 + ((uni-Map.universityArray.length/2) * 30), 400, 25);
			} else {
				uniCheckBoxes[uni].setBounds(30, 45 + (uni * 30), 400, 25);
			}
			
		}

		// creating the submit button
		add(submitButton);
		submitButton.setFont(FontClass.medium25);
		submitButton.setBounds(300, 360, 190, 70);
		submitButton.setBackground(buttonColor);
		submitButton.setBorderPainted(false);
		submitButton.addActionListener(this);
		submitButton.setOpaque(true);
		submitButton.setForeground(Color.white);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		//if the user has selected the universities
		if (e.getSource() == submitButton) {
			// set the frame visibility to false
			setVisible(false);
			// clear the map of all the points
			Map.clearMap();
			// go through all the checkboxes and see which ones were selected
			for (int i = 0; i < uniCheckBoxes.length; i++) {
				if (uniCheckBoxes[i].isSelected()) {
					// then go through all the points and if the point is for the university
					// the user picked, then have it enabled
					for (SwingWaypoint w : Map.waypoints) {
						if (w.getText().equals(uniCheckBoxes[i].getText())) {
							w.getUniLogo().setEnabled(true);
						}
					}
				}
			}
		}
	}
}
