package view;
/* Megan Chun
 * 
 * External Source: https://stackoverflow.com/questions/22965783/how-to-hide-text-when-typing-in-inputdialog (password)
 */
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import java.util.Scanner;

import javax.crypto.spec.SecretKeySpec;
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

public class CreateAccount extends JFrame implements KeyListener, ActionListener {

	//Create instances of controllers
	public FontClass fonts = new FontClass();
	public Encryption encrypt = new Encryption();
	
	private JLabel logo = new JLabel(new ImageIcon("images/logo.png"));
	private JTextField username = new JTextField("  Username");
	private JPasswordField password = new JPasswordField("");
	private JLabel tempPassword = new JLabel("Password");
	private JPasswordField confirmPassword = new JPasswordField("");
	private JLabel tempConfirmPassword = new JLabel("Confirm Password");
	JButton createAccountBtn = new JButton("Create Account");

	public CreateAccount() {

		//Set up the frame
		setSize(1280, 720); //size
		setTitle("Create Account");
		getContentPane().setBackground(Color.WHITE); //background colour
		setLayout(null);  //layout manager
		setLocationRelativeTo(null); //location of frame
	
		logo.setBounds(0, 60, 1280, 97);
		logo.setHorizontalAlignment(JLabel.CENTER);
		add(logo); //add to the screen
		
		JLabel title = new JLabel("Create an account.");
		title.setBounds(0, 160, 1280, 25);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(FontClass.medium25);
		add(title); //add to the screen
		
		JLabel head = new JLabel("Find My Uni");
		head.setBounds(0, 190, 1280, 50);
		head.setHorizontalAlignment(JLabel.CENTER);
		head.setFont(FontClass.semibold50);
		add(head); //add to the screen
		
		//username textfield
		username.addKeyListener(this); //add key listener
		username.setBounds(390, 280, 500, 65); 
		username.setBackground(new Color(243, 244, 245)); //change background
		username.setForeground(new Color(105,105,105)); //change text colour
		username.setHorizontalAlignment(JTextField.LEFT); //align text
		username.setFont(FontClass.medium25); //set font
		username.setBorder(BorderFactory.createEmptyBorder()); //create transparent border
		add(username); //add to the screen
		
		//password textfield
		password.addKeyListener(this);  //add key listener
		password.setEchoChar('•'); 
		password.setBounds(390, 360, 500, 65);
		password.setBackground(new Color(243, 244, 245)); //change background
		password.setForeground(new Color(105,105,105)); //change text colour
		password.setHorizontalAlignment(JTextField.LEFT);  //align text
		password.setFont(FontClass.medium25); //set font
		password.setBorder(BorderFactory.createEmptyBorder());  //create transparent border
		add(password); //add to the screen

		//temporary place holder
		tempPassword.setForeground(new Color(105,105,105));
		tempPassword.setFont(FontClass.medium25);
		tempPassword.setBounds(10, 0, 500, 65);
		password.add(tempPassword); //add to the screen
		
		//password textfield
		confirmPassword.addKeyListener(this);  //add key listener
		confirmPassword.setEchoChar('•'); 
		confirmPassword.setBounds(390, 440, 500, 65);
		confirmPassword.setBackground(new Color(243, 244, 245)); //change background
		confirmPassword.setForeground(new Color(105,105,105)); //change text colour
		confirmPassword.setHorizontalAlignment(JTextField.LEFT);  //align text
		confirmPassword.setFont(FontClass.medium25); //set font
		confirmPassword.setBorder(BorderFactory.createEmptyBorder());  //create transparent border
		add(confirmPassword);
		
		//temporary place holder
		tempConfirmPassword.setForeground(new Color(105,105,105)); //set text colour
		tempConfirmPassword.setFont(FontClass.medium25); //set font
		tempConfirmPassword.setBounds(10, 0, 500, 65); //set bounds
		confirmPassword.add(tempConfirmPassword); //add to the screen
		
		//login button
		createAccountBtn.addActionListener(this);
		createAccountBtn.setBounds(390, 560, 500, 65); //set bounds
		createAccountBtn.setBackground(new Color(57, 90, 154)); //change background
		createAccountBtn.setForeground(Color.WHITE); //change text colour
		createAccountBtn.setHorizontalAlignment(JTextField.CENTER);  //align text
		createAccountBtn.setFont(FontClass.medium25); //set font
		createAccountBtn.setOpaque(true);
		createAccountBtn.setBorder(BorderFactory.createEmptyBorder()); //create empty border
		add(createAccountBtn); //add to the screen
		
	
		setVisible(true);
	}
	//GETTERS AND SETTERS
	public JTextField getUsername() {
		return username;
	}

	public void setUsername(JTextField username) {
		this.username = username;
	}

	public JPasswordField getPassword() {
		return password;
	}

	public void setPassword(JPasswordField password) {
		this.password = password;
	}

	public JPasswordField getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(JPasswordField confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		//if the user is typing in the password field
		if (e.getComponent().getY() == 360) 
			tempPassword.setVisible(false);
		if (e.getComponent().getY() == 440) 
			tempConfirmPassword.setVisible(false);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//if the user clicks the create account button
		if (e.getSource() == createAccountBtn) {
	
			//check to see if the username already exists
			boolean exists = ReadFile.checkAlreadyExists(username.getText());
			
			
			if (!exists) {
				//check to make sure the password length is greater or equal to 6
				if (password.getText().length() >= 6) {
			
					//check to verify the confirm password and password match
					if (confirmPassword.getText().equals(password.getText())) {
						
						new ProfileSetUp(username.getText(), password.getText());
					}
					
					//if the passwords don't match
					else {
						JOptionPane.showMessageDialog(this,
								"Passwords do not match!",
								"Unable To Process Request", JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(this,
							"Passwords needs to be at least 6 characters long!",
							"Unable To Process Request", JOptionPane.ERROR_MESSAGE);
					
				}
				
			}
			else {
				JOptionPane.showMessageDialog(this,
						"This username is already taken!",
						"Unable To Process Request", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	
	
}
