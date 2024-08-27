package view;
/* Megan Chun
 * 
 * External Source: 
 * - https://stackoverflow.com/questions/22965783/how-to-hide-text-when-typing-in-inputdialog (password)
 * - https://mkyong.com/java/how-to-get-the-total-number-of-lines-of-a-file-in-java/ (lines in a file)
 */
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.FontClass;
import controller.GeneralSearchController;
import model.User;

public class MenuBar extends JPanel implements KeyListener, ActionListener {

	FontClass fonts = new FontClass();
	
	User user;
	
	//Initialize and declare the layout manager
	public GridBagConstraints c = new GridBagConstraints();
	
	//Initialize and declare buttons for the menu bar
	public JButton homeBtn = new JButton("Find My Uni");
	public JButton browseBtn = new JButton("Browse");
	public JButton surveyBtn = new JButton("Survey");
	public JButton mapBtn = new JButton("Map");
		
	public MenuBar(User user) {
	
		this.user = user;
		
		//Set up the frame
		setSize(1280, 137); //size
		setBackground(Color.WHITE);
		setLayout(new GridBagLayout());  //layout manager

		//set the layout specification for the home button
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,85,0,500); //add padding to the right
		c.gridx = 0;  //set the alignment
		homeBtn.addActionListener(this);
		homeBtn.setBackground(new Color(50, 50, 50)); //change background
		homeBtn.setHorizontalAlignment(JTextField.LEFT);  //align text
		homeBtn.setOpaque(false);
		homeBtn.setBorder(BorderFactory.createEmptyBorder()); //create empty border
		homeBtn.setFont(FontClass.medium25); //set font
		add(homeBtn,c); //add to the panel
		
		//set the layout specification for the browse button
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,20,0,0);
		c.gridx = 1; //set the alignment
		browseBtn.addActionListener(this);
		browseBtn.setBackground(new Color(50, 50, 50)); //change background
		browseBtn.setHorizontalAlignment(JTextField.RIGHT);  //align text
		browseBtn.setOpaque(false);
		browseBtn.setBorder(BorderFactory.createEmptyBorder()); //create empty border
		browseBtn.setFont(FontClass.medium16); //set font
		add(browseBtn,c); //add to the panel
		
		//set the layout specification for the survey button
		c.insets = new Insets(0,20,0,0);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2; //set the alignment
		surveyBtn.addActionListener(this);
		surveyBtn.setBackground(new Color(50, 50, 50)); //change background
		surveyBtn.setHorizontalAlignment(JTextField.RIGHT);  //align text
		surveyBtn.setOpaque(false);
		surveyBtn.setBorder(BorderFactory.createEmptyBorder()); //create empty border
		surveyBtn.setFont(FontClass.medium16); //set font
		add(surveyBtn,c); //add to the panel
		
		//set the layout specification for the survey button
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,20,0,85);
		c.gridx = 3; //set the alignment
		mapBtn.addActionListener(this);
		mapBtn.setBackground(new Color(50, 50, 50)); //change background
		mapBtn.setHorizontalAlignment(JTextField.RIGHT);  //align text
		mapBtn.setOpaque(false);
		mapBtn.setBorder(BorderFactory.createEmptyBorder()); //create empty border
		mapBtn.setFont(FontClass.medium16); //set font
		add(mapBtn,c); //add to the panel

		setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == browseBtn) {
			
			new GeneralSearchController(user);
			
			
		}
		if (e.getSource() == homeBtn) {
			
			try {
				new Homepage(user);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
	}
	
	
}
