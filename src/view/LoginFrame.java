package view;
/* Megan Chun
 * 
 * External Source: 
 * - https://stackoverflow.com/questions/22965783/how-to-hide-text-when-typing-in-inputdialog (password)
 * - https://mkyong.com/java/how-to-get-the-total-number-of-lines-of-a-file-in-java/ (lines in a file)
 */
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.Encryption;
import controller.FontClass;
import model.ReadFile;
import model.User;

public class LoginFrame extends JFrame implements KeyListener, ActionListener {

	FontClass fonts = new FontClass();
	
	User user;
	
	Encryption encrypt = new Encryption();
	
	JLabel logo = new JLabel(new ImageIcon("images/logo.png"));
	JTextField username = new JTextField("  Username");
	JPasswordField password = new JPasswordField("");
	JLabel tempPassword = new JLabel("Password");
	JButton loginBtn = new JButton("Login");
	JButton signUp = new JButton("<HTML><U>Don’t have an account? Click here to sign up.</U></HTML>");
	
	public LoginFrame() {
	
		//Set up the frame
		setSize(1280, 720); //size
		setTitle("Login");
		getContentPane().setBackground(Color.WHITE); //background colour
		setLayout(null);  //layout manager
		setLocationRelativeTo(null); //location of frame
	
		logo.setBounds(0, 60, 1280, 97);
		logo.setHorizontalAlignment(JLabel.CENTER);
		add(logo);
		
		JLabel title = new JLabel("Welcome to,");
		title.setBounds(0, 160, 1280, 25);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(FontClass.medium25);
		add(title);
		
		JLabel head = new JLabel("Find My Uni");
		head.setBounds(0, 190, 1280, 50);
		head.setHorizontalAlignment(JLabel.CENTER);
		head.setFont(FontClass.semibold50);
		add(head);
		
		//username textfield
		username.addKeyListener(this); //add key listener
		username.setBounds(390, 280, 500, 65); 
		username.setBackground(new Color(243, 244, 245)); //change background
		username.setForeground(new Color(105,105,105)); //change text colour
		username.setHorizontalAlignment(JTextField.LEFT); //align text
		username.setFont(FontClass.medium25); //set font
		username.setBorder(BorderFactory.createEmptyBorder()); //create transparent border
		add(username);
		
		//password textfield
		password.addKeyListener(this);  //add key listener
		password.setEchoChar('•'); 
		password.setBounds(390, 360, 500, 65);
		password.setBackground(new Color(243, 244, 245)); //change background
		password.setForeground(new Color(105,105,105)); //change text colour
		password.setHorizontalAlignment(JTextField.LEFT);  //align text
		password.setFont(FontClass.medium25); //set font
		password.setBorder(BorderFactory.createEmptyBorder());  //create transparent border
		add(password);

		//temporary place holder
		tempPassword.setForeground(new Color(105,105,105));
		tempPassword.setFont(FontClass.medium25);
		tempPassword.setBounds(10, 0, 500, 65);
		password.add(tempPassword);
		
		//login button
		loginBtn.setBounds(390, 480, 500, 65);
		loginBtn.setBackground(new Color(57, 90, 154));
		loginBtn.setForeground(Color.WHITE);
		loginBtn.setHorizontalAlignment(JTextField.CENTER); 
		loginBtn.setFont(FontClass.medium25);
		loginBtn.setOpaque(true);
		loginBtn.addActionListener(this);
		loginBtn.setBorder(BorderFactory.createEmptyBorder());
		add(loginBtn);
		
		signUp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		signUp.addActionListener(this);
		signUp.setBounds(390, 580, 500, 20);
		signUp.setOpaque(false);
		signUp.setForeground(Color.BLACK);
		signUp.setBorder(BorderFactory.createEmptyBorder());
		signUp.setFont(FontClass.medium16);
		add(signUp);
		
		setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		//if the user is typing in the password field
		if (e.getComponent().getY() == 360) 
			tempPassword.setVisible(false);
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
		// TODO Auto-generated method stub
		if (e.getSource() == signUp) {
			new CreateAccount();
			this.dispose();
		}
		
		if (e.getSource() == loginBtn) {
			if(validLoginInfo()) {
				try {
					new Homepage(user);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				this.dispose();
			}
			else {
				JOptionPane.showMessageDialog(this,
						"Invalid Login Information",
						"Unable To Process Request", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public boolean validLoginInfo() {
		
		int lines = ReadFile.numLines("data/accounts.txt");
	    
		//read the file of previously created login info
		try {
			// Create Scanner object to get the file
			Scanner inputFile = new Scanner(new File("data/accounts.txt"));
			
			for (int i = 0; i < lines; i++) {
				
				//set a delimiter which will stop scanning the data for one row of progress
				inputFile.useDelimiter(",|\r\n");
				
				//get the username and password in the file and turn it into a byte array
				String usernameStr = inputFile.next();
				String passwordStr = inputFile.next();
				String firstName = inputFile.next();
				String lastName = inputFile.next();
				String emailAddress = inputFile.next();
				String streetNum = inputFile.next();
				String street = inputFile.next();
				String city = inputFile.next();
				String postalCode = inputFile.next();
				String fieldOfStudy = inputFile.next();
				
				if (usernameStr.equals(username.getText())) {
					if (passwordStr.equals(password.getText())) {
						user = new User(usernameStr,passwordStr,firstName,lastName,emailAddress,streetNum,street,city,postalCode,fieldOfStudy);
						return true;
					}
				}
			}
		
			inputFile.close(); //close the scanner 
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		
		return false;
		
	}
}
