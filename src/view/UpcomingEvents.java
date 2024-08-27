package view;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jsoup.HttpStatusException;

import controller.FontClass;
import controller.WebScrapping;
import model.User;

public class UpcomingEvents extends JPanel implements ActionListener {

	public JPanel[] events = new JPanel[4];
	public JLabel[] eventsBackground = new JLabel[4];
	public JButton[] eventsBtns = new JButton[4];
	
	//Initialize and declare the layout manager
	public GridBagConstraints c = new GridBagConstraints();
	
	//create a 2D array for the upcoming events
	public static String[][] upcomingEvents = new String[4][5];

	public UpcomingEvents(User user) throws HttpStatusException, IOException  {
		
		upcomingEvents = WebScrapping.getDataEvents();
		
		setSize(740,265);
		setLayout(null);
		setBackground(Color.WHITE);
		
		int index = 0;
		
		for (int x = 0; x <= 564; x += 188) {
			
			eventsBackground[index] = new JLabel(new ImageIcon("images/eventsBackground.png"));
			eventsBackground[index].setLayout(new GridBagLayout());
			eventsBackground[index].setBounds(x, 0, 162, 265);
			add(eventsBackground[index]);
			
			c.weightx = 0.5;
			
			c.gridx = 0;  //set the alignment
			c.gridy = 0;  //set the alignment
			c.gridwidth = 2;
			c.weighty = 1;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.anchor = GridBagConstraints.PAGE_START; //top of space
			c.insets = new Insets(16,16,0,16); //add padding to the right
			//JLabel img = new JLabel(new ImageIcon("images/tempImage.png"));
		
			ImageIcon icon = new ImageIcon("images/uniCampus/"+upcomingEvents[index][0]+".jpeg");
			Image scaleImage = icon.getImage().getScaledInstance(130, 86,Image.SCALE_DEFAULT);
			JLabel uniImg = new JLabel(new ImageIcon (scaleImage));
			eventsBackground[index].add(uniImg, c);
			
			//set the layout specification for the university name
		
			c.gridy = 1;  //set the alignment
			c.insets = new Insets(0,16,0,16); //add padding to the right
			c.anchor = GridBagConstraints.PAGE_START; //top of space
			JTextArea uniName = new JTextArea(upcomingEvents[index][0]);
			uniName.setOpaque(false);
			uniName.setWrapStyleWord(true);
			uniName.setLineWrap(true);
			uniName.setForeground(new Color(57,90,154)); //change text colour
			uniName.setFont(FontClass.medium10); //set font
			eventsBackground[index].add(uniName, c);
			
			c.weighty = 1;
			c.gridy = 2;  //set the alignment
			JTextArea eventName = new JTextArea(upcomingEvents[index][1]);
			eventName.setOpaque(false);
			eventName.setWrapStyleWord(true);
			eventName.setLineWrap(true);
			eventName.setForeground(new Color(50,50,50)); //change text colour
			eventName.setFont(FontClass.medium12); //set font
			eventsBackground[index].add(eventName, c);
			
			
			//set the time and date
			c.gridx = 0;
			c.gridy = 3;
			c.gridwidth = 1;
			JLabel dateImg = new JLabel(new ImageIcon("images/date.png"));
			eventsBackground[index].add(dateImg, c);
			
			c.gridx = 1;  //set the alignment
			JTextArea date = new JTextArea(upcomingEvents[index][2]);
			date.setOpaque(false);
			date.setEditable(false);
			date.setWrapStyleWord(true);
			date.setLineWrap(true);
			date.setForeground(new Color(105,105,105)); //change text colour
			date.setFont(FontClass.medium10); //set font
			eventsBackground[index].add(date, c);

			c.gridx = 0;
			c.gridy = 4;
			c.gridwidth = 1;
			JLabel timeImg = new JLabel(new ImageIcon("images/time.png"));
			eventsBackground[index].add(timeImg, c);
			
			c.gridx = 1;  //set the alignment
			JTextArea time = new JTextArea(upcomingEvents[index][3]);
			time.setOpaque(false);
			time.setEditable(false);
			time.setWrapStyleWord(true);
			time.setLineWrap(true);
			time.setForeground(new Color(105,105,105)); //change text colour
			time.setFont(FontClass.medium10); //set font
			eventsBackground[index].add(time, c);
			
			c.gridx = 0;
			c.gridy = 5;
			c.gridwidth = 2;
			c.weighty = 0;
			c.ipady = 15;
			c.insets = new Insets(0,16,16,16); //add padding to the right
			c.anchor = GridBagConstraints.PAGE_END; //bottom of space
			eventsBtns[index] = new JButton("Learn More");
			eventsBtns[index].setSize(eventsBtns[index].getWidth(), 50);
			eventsBtns[index].setForeground(Color.WHITE);
			eventsBtns[index].setFont(FontClass.medium10);
			eventsBtns[index].setBackground(new Color(57,90,154));
			eventsBtns[index].setHorizontalAlignment(JTextField.CENTER); 
			eventsBtns[index].setOpaque(true);
			eventsBtns[index].addActionListener(this);
			eventsBtns[index].setBorder(BorderFactory.createEmptyBorder());
			eventsBackground[index].add(eventsBtns[index], c);
		
			index++; 
		}
		
		
		setVisible(true);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 4; i++) {
			
			if (e.getSource() == eventsBtns[i]) {
				
				try {
					Desktop.getDesktop().browse(new URI("https://www.ontariouniversitiesinfo.ca/"+upcomingEvents[i][4]));
				} catch (IOException | URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
	}
}
