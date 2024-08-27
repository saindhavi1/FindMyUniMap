package view;

import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

import controller.FontClass;
import controller.GeneralSearchController;
import model.University;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;
import java.net.URI;

//This class represents a point that will be placed on a map
//Source: https://github.com/msteiger/jxmapviewer2
public class SwingWaypoint extends DefaultWaypoint implements ActionListener {
	FontClass fonts = new FontClass();
	private final JButton uniLogo;
	private String text;

	JFrame uniFrame = new JFrame();
	JLabel uniNameLabel = new JLabel("");
	JLabel uniPicture = new JLabel(new ImageIcon());
	JLabel addressLabel = new JLabel("<html><u>Address</u></html>");
	JTextArea uniAddress = new JTextArea();
	JLabel phoneLabel = new JLabel("<html><u>Phone Number</u></html>");
	JLabel uniPhoneNumLabel = new JLabel();
	JButton learnMore = new JButton("Learn More");
	JButton viewPrograms = new JButton("View Programs");

	String uniWebsite;

	public SwingWaypoint(String text, GeoPosition coord) {
		super(coord);
		this.text = text;

		uniFrame.setTitle(text);

		// add the image to the button/waypoint
		uniLogo = new JButton(new ImageIcon(scalePic("Logo/" + withoutDash(text) + "Logo.png", 24, 24)));

		// style the button/waypoint
		uniLogo.setBorder(new EmptyBorder(0, 0, 0, 0));
		uniLogo.setOpaque(true);
		uniLogo.setSize(24, 24);
		uniLogo.addActionListener(this);
		uniLogo.setVisible(true);

		// style the frame that appears when you click on the university's waypoint
		uniFrame.setSize(600, 300);
		uniFrame.setLocationRelativeTo(null);
		uniFrame.setBackground(new Color(243, 244, 245));
		uniFrame.setLayout(null);

		// Style the label of the name of the university
		uniNameLabel.setBounds(0, 10, 600, 30);
		uniNameLabel.setHorizontalAlignment(JLabel.CENTER);
		uniNameLabel.setFont(FontClass.medium25);
		uniFrame.add(uniNameLabel);

		// Style the picture of the university
		uniPicture.setBounds(13, 45, 270, 220);
		uniFrame.add(uniPicture);

		// Style the label address label
		addressLabel.setBounds(300, 50, 290, 20);
		addressLabel.setHorizontalAlignment(JLabel.CENTER);
		addressLabel.setFont(FontClass.semibold18);
		uniFrame.add(addressLabel);

		// Style the label of the address of the university
		uniAddress.setBounds(290, 75, 300, 70);
		uniAddress.setFont(FontClass.regular16);
		uniAddress.setEditable(false);
		uniAddress.setOpaque(false);
		uniAddress.setWrapStyleWord(true);
		uniAddress.setLineWrap(true);
		uniFrame.add(uniAddress);

		// Style the phone number label
		phoneLabel.setBounds(300, 140, 290, 20);
		phoneLabel.setHorizontalAlignment(JLabel.CENTER);
		phoneLabel.setFont(FontClass.semibold18);
		uniFrame.add(phoneLabel);

		// Style the label of the phone number of the university
		uniPhoneNumLabel.setBounds(300, 170, 290, 20);
		uniPhoneNumLabel.setHorizontalAlignment(JLabel.CENTER);
		uniPhoneNumLabel.setFont(FontClass.regular16);
		uniFrame.add(uniPhoneNumLabel);

		// Style the label of the button that will take the user to the website of the
		// university
		learnMore.setFont(FontClass.regular16);
		learnMore.setBounds(288, 200, 140, 60);
		learnMore.setBackground(new Color(57, 90, 154));
		learnMore.setBorderPainted(false);
		learnMore.setOpaque(true);
		learnMore.setForeground(Color.white);
		learnMore.addActionListener(this);
		uniFrame.add(learnMore);

		//Style the button that will lead to the browse frame already
		//filtered with the university
		viewPrograms.setFont(FontClass.regular16);
		viewPrograms.setBounds(433, 200, 160, 60);
		viewPrograms.setBackground(new Color(57, 90, 154));
		viewPrograms.setBorderPainted(false);
		viewPrograms.setOpaque(true);
		viewPrograms.setForeground(Color.white);
		viewPrograms.addActionListener(this);
		uniFrame.add(viewPrograms);

		uniFrame.setVisible(false);
	}

	// This method will make sure that the same logo will be displayed
	// universities with multiple campuses
	public String withoutDash(String text) {
		if (text.contains("-")) {

			String withoutDash = text.substring(0, (text.indexOf("-") - 1));
			return withoutDash;
		} else
			return text;
	}
	// method that will scale an image
		private Image scalePic(String imagePath, int x, int y) {
			ImageIcon imageIcon = new ImageIcon(imagePath);
			Image imageScaled = imageIcon.getImage().getScaledInstance(x, y, Image.SCALE_SMOOTH);
			return imageScaled;
		}

	//getters and setters
	JButton getUniLogo() {
		return uniLogo;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == uniLogo) {
			// Create the panel for each university
			// Set the university's name as the title
			uniNameLabel.setText(text);
			// set the image as the icon for the jlabel
			uniPicture.setIcon(new ImageIcon(scalePic("UniCampus/" + text + ".jpeg", 270, 220)));

			// check every university in the array
			for (University university : Map.universityArray) {
				// if the university the user clicked on is the same as one in the university
				// array
				// then display the appropriate information
				if (text.equals(university.getName())) {

					uniAddress.setText(university.getAddress());
					uniPhoneNumLabel.setText(university.getPhoneNumber());
					uniWebsite = university.getLink();
				}
			}
			uniFrame.setVisible(true);
		} else if (e.getSource() == learnMore) {

			// If the user wants to learn more and visit the university's website
			// get the desktop and open the link in their desktop
			Desktop desktop = Desktop.getDesktop();

			try {
				desktop.browse(URI.create(uniWebsite));
			} catch (IOException e1) {

				e1.printStackTrace();
			}

		} 
		//if the user wants to view all the programs of that university
		//A group member's cod
//		else if (e.getSource() == viewPrograms) {
//			//create the browse frame
//			User user;
//			GeneralSearchController search = new GeneralSearchController(user);
//			search.getFrame().getProgramsPanel().removeAll();
//			SortByFrame sortFrame = new SortByFrame(search);
//			sortFrame.setVisible(false);
//			//add the university's name to the filter values
//			sortFrame.values.add(withoutDash(text));
//			//filter the programs so that only the programs
//			//from that university shows up
//			sortFrame.addNewPrograms();
//			uniFrame.setVisible(false);
//		}

	}
}
