package view;

import java.awt.Color;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.jsoup.HttpStatusException;

import controller.FontClass;
import model.User;

public class Homepage extends JFrame {

	MenuBar menuBar;
	UpcomingEvents events;
	
	public Homepage(User user) throws HttpStatusException, IOException{
		
		menuBar = new MenuBar(user);
		events = new UpcomingEvents(user);
	
		//Set up the frame
		setSize(1280, 720); //size
		setTitle("Home");
		getContentPane().setBackground(Color.WHITE); //background colour
		setLayout(null);  //layout manager
		setLocationRelativeTo(null); //location of frame
		
		add(menuBar);
		
		//add the university image
		JLabel uniImage = new JLabel(new ImageIcon("images/uniImage.png"));
		uniImage.setBounds(83,137,1114,173);
		add(uniImage);
		
		//add the subtitle of the page
		JLabel subTitle = new JLabel("Find the university for you.");
		subTitle.setBounds(0, 0, 1114, 173);
		subTitle.setForeground(Color.WHITE);
		subTitle.setHorizontalAlignment(JLabel.CENTER);
		subTitle.setFont(FontClass.semibold50);
		uniImage.add(subTitle);
		
		//add the map images
		JLabel map = new JLabel(new ImageIcon("images/mapImage.png"));
		map.setBounds(864,335,333,313);
		add(map);
		
		//add the subtitle for the upcoming events
		JLabel eventsTitle = new JLabel("Hi "+user.getFirstName()+", check out upcoming events...");
		eventsTitle.setBounds(83, 335, 380, 30);
		eventsTitle.setHorizontalAlignment(JLabel.LEFT);
		eventsTitle.setFont(FontClass.medium20);
		add(eventsTitle);
		
		events.setBounds(83, 383, events.getWidth(), events.getHeight());
		add(events);
		
		setVisible(true);
		
	}
	

}
