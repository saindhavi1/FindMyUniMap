package view;
import java.util.*;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.FontClass;
import model.User;


public class ProfileSetUp extends JFrame implements ActionListener, ItemListener  {
	
	//Create a user
	private String username;
	private String password;
		
	FontClass fonts = new FontClass();
	
	//Initialize and declare a panel for the questions
	public JPanel questionPanel = new JPanel();
	
	//Initialize and declare the layout manager
	public GridBagConstraints c = new GridBagConstraints();
	
	//Initialize and declare text fields
	JTextField firstNameField = new JTextField("  First Name");
	JTextField lastNameField = new JTextField("  Last Name");
	JTextField emailField = new JTextField("  Email");
	
	//Initialize and declare the data and buttons for the address info
	public static String[] addressInfo = {"Street Number", "Street Name", "City", "Postal Code"};
	public static JTextField[] addressFields = new JTextField[4];
	
	//Initialize and declare the data and buttons for the fields info
	public static String[] typesOfFields = {"Engineering", "Computer Science", "Sciences", "Mathematics"};
	public static JRadioButton[] fieldsBtns = new JRadioButton[4];
	public static ButtonGroup btnGroup = new ButtonGroup();
	
	//Initialize and declare next button
	public JButton nextBtn = new JButton(new ImageIcon("images/nextBtn.png"));
	
	public ProfileSetUp(String username, String password) {
		
		this.username = username;
		this.password = password;
		
		//Set up the frame
		setSize(1280, 720); //size
		setTitle("Profile Set Up"); //title
		getContentPane().setBackground(new Color(57,90,154)); //background colour
		setLayout(null);  //layout manager
		setLocationRelativeTo(null); //location of frame
		
		questionPanel.setSize(900,720);
		questionPanel.setBackground(Color.WHITE);
		add(questionPanel);
		questionPanel.setLayout(new GridBagLayout());
		
		//set the layout specification for the title headers button
		c.weightx = 0.9;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,50,10,0); //add padding to the right
		c.gridx = 0;  //set the alignment
		c.gridwidth = 4;
		JLabel title = new JLabel("Set up your preferences to begin...");
		title.setForeground(new Color(50,50,50)); //change text colour
		title.setHorizontalAlignment(JTextField.LEFT); //align text
		title.setFont(FontClass.medium25); //set font
		questionPanel.add(title, c);	
	
		//Title label for the contact info
		c.gridy = 4;
		c.insets = new Insets(0,50,0,0); 
		JLabel contactTitle = new JLabel("Email");
		contactTitle.setForeground(new Color(50,50,50)); //change text colour
		contactTitle.setHorizontalAlignment(JTextField.LEFT); //align text
		contactTitle.setFont(FontClass.medium20); //set font
		questionPanel.add(contactTitle,c);
		
		//Title label for the address info
		c.gridy = 6;
		JLabel addressTitle = new JLabel("Address Info");
		c.insets = new Insets(0,50,10,0); //add padding to the right
		addressTitle.setForeground(new Color(50,50,50)); //change text colour
		addressTitle.setHorizontalAlignment(JTextField.LEFT); //align text
		addressTitle.setFont(FontClass.medium25); //set font
		questionPanel.add(addressTitle,c);
		
		//Title label for the field of study info
		c.gridy = 9;
		JLabel fieldPreference = new JLabel("Field Of Preference");
		fieldPreference.setForeground(new Color(50,50,50)); //change text colour
		fieldPreference.setHorizontalAlignment(JTextField.LEFT); //align text
		fieldPreference.setFont(FontClass.medium25); //set font
		questionPanel.add(fieldPreference,c);
		
		//Add the titles for the questions
		//Title label for the first name
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.insets = new Insets(0,50,0,0); 
		JLabel firstName = new JLabel("First Name");
		firstName.setForeground(new Color(50,50,50)); //change text colour
		firstName.setHorizontalAlignment(JTextField.LEFT); //align text
		firstName.setFont(FontClass.medium20); //set font
		questionPanel.add(firstName,c);
		
		c.gridy = 2;
		c.ipady = 20;
		c.ipadx = 10;
		c.insets = new Insets(0,50,50,50); 
		firstNameField.addActionListener(this);
		firstNameField.setBackground(new Color(243, 244, 245)); //change background
		firstNameField.setForeground(new Color(105,105,105)); //change text colour
		firstNameField.setHorizontalAlignment(JTextField.LEFT); //align text
		firstNameField.setFont(FontClass.medium20); //set font
		firstNameField.setBorder(BorderFactory.createEmptyBorder()); //create transparent border
		questionPanel.add(firstNameField, c);
		
		//Title label for the last name
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 2;
		c.ipady = 0;
		c.ipadx = 0;
		c.insets = new Insets(0,0,0,0); /// remove padding
		JLabel lastName = new JLabel("Last Name");
		lastName.setForeground(new Color(50,50,50)); //change text colour
		lastName.setHorizontalAlignment(JTextField.LEFT); //align text
		lastName.setFont(FontClass.medium20); //set font
		questionPanel.add(lastName,c);
		
		//Text field for the last name
		c.gridy = 2;
		c.ipady = 20;
		c.ipadx = 10;
		c.insets = new Insets(0,0,50,50); 
		lastNameField.addActionListener(this); // add action listener
		lastNameField.setBackground(new Color(243, 244, 245)); //change background
		lastNameField.setForeground(new Color(105,105,105)); //change text colour
		lastNameField.setHorizontalAlignment(JTextField.LEFT); //align text
		lastNameField.setFont(FontClass.medium20); //set font
		lastNameField.setBorder(BorderFactory.createEmptyBorder()); //create transparent border
		questionPanel.add(lastNameField, c);
		
		//Text field for the email address
		c.gridx = 0;
		c.gridy = 5;
		c.ipady = 20;
		c.ipadx = 10;
		c.gridwidth = 4;
		c.insets = new Insets(0,50,50,50); 
		emailField.addActionListener(this); // add action listener
		emailField.setBackground(new Color(243, 244, 245)); //change background
		emailField.setForeground(new Color(105,105,105)); //change text colour
		emailField.setHorizontalAlignment(JTextField.LEFT); //align text
		emailField.setFont(FontClass.medium20); //set font
		emailField.setBorder(BorderFactory.createEmptyBorder()); //create transparent border
		questionPanel.add(emailField, c);
		
		int x = 0;
		//add the address titles and text fields
		for (int i = 0; i < 4; i++) {
		
			
			//Title label for the address
			c.gridx = x;
			c.gridy = 7;
			c.gridwidth = 1;
			c.ipady = 0;
			c.ipadx = 0;
			if (x == 0)
				c.insets = new Insets(0,50,0,50); 
			else 
				c.insets = new Insets(0,0,0,50);
			JLabel addressLabel = new JLabel(addressInfo[i]);
			addressLabel.setForeground(new Color(50,50,50)); //change text colour
			addressLabel.setHorizontalAlignment(JTextField.LEFT); //align text
			addressLabel.setFont(FontClass.medium20); //set font
			questionPanel.add(addressLabel,c);
			
			//Text field for the address
			c.gridx = x;
			c.gridy = 8;
			c.ipady = 20;
			c.ipadx = 10;
			if (x == 0)
				c.insets = new Insets(0,50,50,50); 
			else 
				c.insets = new Insets(0,0,50,50); 
			addressFields[i] = new JTextField("  "+addressInfo[i]);
			addressFields[i].addActionListener(this); // add action listener
			addressFields[i].setBackground(new Color(243, 244, 245)); //change background
			addressFields[i].setForeground(new Color(105,105,105)); //change text colour
			addressFields[i].setHorizontalAlignment(JTextField.LEFT); //align text
			addressFields[i].setFont(FontClass.medium20); //set font
			addressFields[i].setBorder(BorderFactory.createEmptyBorder()); //create transparent border
			questionPanel.add(addressFields[i], c);
			
			c.gridx = x;
			c.gridy = 10;
			c.ipady = 20;
			c.ipadx = 10;
			if (x == 0)
				c.insets = new Insets(0,50,50,25); 
			else 
				c.insets = new Insets(0,0,50,25); 
			fieldsBtns[i] = new JRadioButton();
			btnGroup.add(fieldsBtns[i]);
			fieldsBtns[i].setIcon(new ImageIcon("images/"+typesOfFields[i]+"Btn.png"));
			fieldsBtns[i].addActionListener(this); // add action listener
			fieldsBtns[i].addItemListener(this);
			fieldsBtns[i].setBorder(BorderFactory.createEmptyBorder()); //create transparent border
			questionPanel.add(fieldsBtns[i], c);
			
			x++;
		}
		
		//add next button
		nextBtn.addActionListener(this);
		nextBtn.setBounds(1060, 300, 58, 58);
		nextBtn.setBorder(BorderFactory.createEmptyBorder()); //create transparent border
		add(nextBtn);
		
		setVisible(true);
		
		
		
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		if (e.getSource() == nextBtn) {
			
			
			int fieldNum = -1;
			String[] fieldFileName = {"engPrograms.txt","compSciPrograms.txt","sciencePrograms","mathPrograms"};
			String fieldType = null;
			
			//determine which field was clicked 
			for (int i = 0; i < 4; i++) {
				
				if (fieldsBtns[i].isSelected()) {
					fieldNum = i;
					fieldType = fieldFileName[i];
				}
			}
			
			//if the user has selected a field type
			if (fieldNum != -1) {
		
				User newUser = new User(username, password, firstNameField.getText(), lastNameField.getText(), emailField.getText(),
						addressFields[0].getText(),addressFields[1].getText(),addressFields[2].getText(),addressFields[3].getText(),
						fieldType);
				
				addToFile("data/accounts.txt",newUser);
				
				try {
					new Homepage(newUser);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else {
				System.out.println("Need to select a field");
			}
		}
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
	    
		if (e.getStateChange() == ItemEvent.SELECTED) {
			for (int i = 0; i < 4; i++) {
				if (fieldsBtns[i] == e.getSource()) {
					fieldsBtns[i].setIcon(new ImageIcon("images/"+typesOfFields[i]+"SelectedBtn.png"));
				}
			}
	    }
	    else if (e.getStateChange() == ItemEvent.DESELECTED) {
	    	for (int i = 0; i < 4; i++) {
				if (fieldsBtns[i] == e.getSource()) {
					fieldsBtns[i].setIcon(new ImageIcon("images/"+typesOfFields[i]+"Btn.png"));
				}
			}
	    }
	}
	

	public void addToFile(String fileName, User user) {
		
		FileWriter fr = null;
		
		try {
			fr = new FileWriter(fileName, true);
			
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		BufferedWriter br = new BufferedWriter(fr);
		try {
		
			String info = new String(user.getUserName())+","+user.getPassword()+","+user.getFirstName()+","+user.getLastName()
									+","+user.getEmailAddress()+","+user.getNumAddress()+","+user.getStreet()+","+user.getCity()+","+user.getPostalCode()
									+","+user.getFieldOfStudy();
			
			br.write("\r\n"+info);
		
			br.close();
			fr.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
}
