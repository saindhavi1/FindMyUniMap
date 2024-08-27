package view;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.WaypointPainter;

import controller.FontClass;
import model.FileReader;
import model.University;
import model.User;

//This class is the GUI component for the map, which will show all the universities and
//their campuses and information about them
//Source to displaying the map: https://github.com/msteiger/jxmapviewer2

public class Map extends JFrame implements ActionListener {

	//University Array
	public static University[] universityArray = new University[19];
	User user;
	FontClass fonts = new FontClass();
	MenuBar menuBar = new MenuBar(user);

	JLabel mapTitle = new JLabel("The Map to Your Future!");
	
	// Make a new map
	public static JXMapViewer mapViewer = new JXMapViewer();
	GeoPosition toronto = new GeoPosition(43.637909, -78.8761158);

	// Create the Help frame/button
	JButton helpButton = new JButton("Help");
	JFrame helpFrame = new JFrame();
	JLabel helpTitle = new JLabel("How to Use the Map");
	JLabel helpDescription = new JLabel("<html><ul><li>Drag the map to see the surroundings</li>"
			+ "<li>Scroll down to zoom in and scroll up to zoom out</li>"
			+ "<li>Click on the university's logo to view the university's information</li>"
			+ "<li>Explore the filter section to filter the universities shown on the map!</li>" + "</ul></html>");
	JLabel helpPic = new JLabel(new ImageIcon("Images/helpPic.png"));
	
	// Create the filter section of the map
	JPanel filterPanel = new JPanel();
	JLabel filterTitle = new JLabel("Filter");
	JButton allUniButton = new JButton("All Universities");
	//Create a Hash Set of points where the universities will be
	public static Set<SwingWaypoint> waypoints = new HashSet<SwingWaypoint>();

	//Create the filter buttons
	JButton savedUniButton = new JButton("View Saved Universities");
	JButton withinDistanceButton = new JButton("Within Distance");
	JButton selectUniButton = new JButton("Select University");
	Color buttonColor = new Color(57, 90, 154);

	// get the user's location
	
	//ALSO THIS
	GeoPosition userAddress = new GeoPosition(43.8971, -79.2786);

	private static final double EARTH_RADIUS = 6371;

	//NEED TO UPDATE THIS
	int[] savedPrograms = { 657, 7534, 7657, 383 };

	FileReader programs = new FileReader();

	public Map() {

		// Create a TileFactoryInfo for OpenStreetMap
		TileFactoryInfo info = new OSMTileFactoryInfo();
		DefaultTileFactory tileFactory = new DefaultTileFactory(info);

		mapViewer.setTileFactory(tileFactory);

		// Use 8 threads in parallel to load the tiles
		tileFactory.setThreadPoolSize(8);

		// Set the focus
		mapViewer.setZoom(12);
		mapViewer.setAddressLocation(toronto);

		// Saindhavi: Allow the user to be able to drag around the map
		MouseInputListener mm = new PanMouseInputListener(mapViewer);
		mapViewer.addMouseListener(mm);
		mapViewer.addMouseMotionListener(mm);
		mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapViewer));

		// Saindhavi: Set the bounds for the map
		mapViewer.setBounds(25, 160, 950, 500);

		// Display the viewer in a JFrame
		setTitle("The Map to Your Future!");
		setLayout(null);
		getContentPane().add(mapViewer);
		getContentPane().setBackground(Color.WHITE);
		setSize(1280, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		// Everything after this is Saindhavi's work

		// Creating the title of the map page
		mapTitle.setFont(FontClass.semibold50);
		mapTitle.setBounds(350, 100, 1000, 50);
		add(mapTitle);

		//Creating the menu bar at the top of the Map page
		menuBar.setBounds(0, 0, 1280, 100);
		menuBar.setVisible(true);
		add(menuBar);

		//Creating a help section to learn how to navigate the map
		helpButton.setBounds(30, 100, 100, 50);
		helpButton.setFont(FontClass.medium25);
		helpButton.setBackground(buttonColor);
		helpButton.setBorderPainted(false);
		helpButton.setOpaque(true);
		helpButton.setForeground(Color.white);
		helpButton.addActionListener(this);
		add(helpButton);

		helpFrame.setSize(700, 500);
		helpFrame.setLocationRelativeTo(null);
		helpFrame.setTitle("Help");
		helpFrame.setVisible(false);
		helpFrame.setLayout(null);

		helpTitle.setBounds(200, 10, 700, 50);
		helpTitle.setFont(FontClass.semibold30);
		helpFrame.add(helpTitle);

		helpDescription.setBounds(10, 15, 1000, 200);
		helpDescription.setFont(FontClass.medium16);
		helpFrame.add(helpDescription);
		
		ImageIcon imageIcon = new ImageIcon("Images/helpPic.png");
		Image imageScaled = imageIcon.getImage().getScaledInstance(680, 290, Image.SCALE_SMOOTH);
		helpPic.setIcon(new ImageIcon(imageScaled));
		helpPic.setBounds(10, 170, 680, 290);
		
		helpFrame.add(helpPic);

		// Creating a filter panel where the filter buttons will go
		filterPanel.setBounds(975, 160, 280, 500);
		filterPanel.setLayout(null);
		filterPanel.setVisible(true);
		filterPanel.setBackground(new Color(243, 243, 245));
		// new Color(243, 243, 245)
		add(filterPanel);

		// Creating the title of the filter section
		filterTitle.setFont(FontClass.medium25);
		filterTitle.setBounds(110, 25, 200, 25);
		filterPanel.add(filterTitle);

		// Creating all the buttons for the filter panel
		allUniButton.setFont(FontClass.medium25);
		allUniButton.setBounds(0, 65, 280, 100);
		allUniButton.setBackground(buttonColor);
		allUniButton.setBorderPainted(false);
		allUniButton.setOpaque(true);
		allUniButton.setForeground(Color.white);
		allUniButton.addActionListener(this);
		filterPanel.add(allUniButton);

		savedUniButton.setFont(FontClass.medium20);
		savedUniButton.setBounds(0, 175, 280, 100);
		savedUniButton.setBackground(buttonColor);
		savedUniButton.setBorderPainted(false);
		savedUniButton.setOpaque(true);
		savedUniButton.setForeground(Color.white);
		savedUniButton.addActionListener(this);
		filterPanel.add(savedUniButton);

		withinDistanceButton.setFont(FontClass.medium25);
		withinDistanceButton.setBounds(0, 285, 280, 100);
		withinDistanceButton.setBackground(buttonColor);
		withinDistanceButton.setBorderPainted(false);
		withinDistanceButton.setOpaque(true);
		withinDistanceButton.setForeground(Color.white);
		withinDistanceButton.addActionListener(this);
		filterPanel.add(withinDistanceButton);

		selectUniButton.setFont(FontClass.medium25);
		selectUniButton.setBounds(0, 395, 280, 100);
		selectUniButton.setBackground(buttonColor);
		selectUniButton.setBorderPainted(false);
		selectUniButton.setOpaque(true);
		selectUniButton.setForeground(Color.white);
		selectUniButton.addActionListener(this);
		filterPanel.add(selectUniButton);

		// A problem I experienced in my code is that my buttons would sometimes
		// disappear when running the code, this is to make sure this doesn't occur
		filterPanel.repaint();
		// The same problem occurred with the menu bar, which is why I added this code
		revalidate();

		// create waypoints (points) on the map for each university in the university
		// array
		for (University university : universityArray) {
			waypoints.add(new SwingWaypoint(university.getName(), university.getCoordinates()));
		}
		
		// Create a point for the user's place
		waypoints.add(new SwingWaypoint("User", userAddress));
		// Set the overlay painter
		WaypointPainter<SwingWaypoint> swingWaypointPainter = new SwingWaypointOverlayPainter();
		swingWaypointPainter.setWaypoints(waypoints);
		mapViewer.setOverlayPainter(swingWaypointPainter);

		// Add the JButtons to the map viewer
		for (SwingWaypoint w : waypoints) {
			mapViewer.add(w.getUniLogo());
		}
	}

	// This method will clear the map of all the points
	public static void clearMap() {
		//go through all the points
		for (SwingWaypoint w : waypoints) {
			//set them enabled to false so that they fade
			w.getUniLogo().setEnabled(false);
			//However if the point is the user's point, then always
			//keep it enabled/visable
			if (w.getText().equals("User")) {
				w.getUniLogo().setEnabled(true);
			}

		}
	}

	// got the formula from:
	// https://www.baeldung.com/java-find-distance-between-points

	// This method is used to calculate the haversine of a value
	double haversine(double val) {
		return Math.pow(Math.sin(val / 2), 2);
	}

	// this method calculates the distance between 2 points (in longitude and
	// latitude) and
	// and will return a boolean value if it is within 100km or not
	public boolean isWithin100km(double startLat, double startLong, double endLat, double endLong, String distance) {

		double dLat = Math.toRadians((endLat - startLat));
		double dLong = Math.toRadians((endLong - startLong));

		startLat = Math.toRadians(startLat);
		endLat = Math.toRadians(endLat);

		double a = haversine(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversine(dLong);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		if ((EARTH_RADIUS * c) < Integer.parseInt(distance))
			return true;
		else
			return false;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//if the user wants to select a university to see on the map
		if (e.getSource() == selectUniButton) {
			//open the select university frame
			new SelectUniversity();
		} 
		//if the user wants to see all the universities
		else if (e.getSource() == allUniButton) {
			//Go through all the points and set them enabled to true
			for (SwingWaypoint w : waypoints) {
				w.getUniLogo().setEnabled(true);
				
			}

		} 
		//if the user wants to filter based on distance
		else if (e.getSource() == withinDistanceButton) {
			//create a dropdown JOptionPane of possible distances
			String[] distanceOptions = { "75" ,"100", "200"};
			String distance = (String) JOptionPane.showInputDialog(this, "Set the distance.", "Distance",
					JOptionPane.PLAIN_MESSAGE, null, distanceOptions, distanceOptions[0]);

			//if the user selects a distance, then filter the points
			if (distance != null) {
				// clear the map of all the points
				clearMap();
				// loop through all the universities
				for (University university : universityArray) {
					// if the university is within the selected distance of the user's location
					// then go through the waypoints and set the university's waypoint enable to true
					if (isWithin100km(userAddress.getLatitude(), userAddress.getLongitude(),
							university.getCoordinates().getLatitude(), university.getCoordinates().getLongitude(),
							distance)) {
						for (SwingWaypoint w : waypoints) {
							if (university.getName().equals(w.getText()))
								w.getUniLogo().setEnabled(true);
							
						}
					}

				}
			}

		} 
		//if the user just wants to see universities they saved
		else if (e.getSource() == savedUniButton) {
			
			//clear all the points on the map
			clearMap();

			//go through each of the saved programs and look through the program numbers in the program
			//array 
			
			//Are architecture programs in here?
			for (int i = 0; i < savedPrograms.length; i++) {
				for (int j = 0; j < programs.getProgramArray().size(); j++) {
					//if the program is the same as the program the user saved (checked using their numbers)
					if (savedPrograms[i] == (programs.getProgramArray().get(j).getProgramNum())) {
						//then go through all the points and set the university's button to visable
						for (SwingWaypoint w : waypoints) {
							System.out.println(w.getText());
							System.out.println(programs.getProgramArray().get(j).getUniversity());
							if (w.getText().equals(programs.getProgramArray().get(j).getUniversity().replace("–", "-"))) {
								w.getUniLogo().setEnabled(true);
								break;
							}
						}
						break;
					}
				}
			}

		} 
		//if the user wants to see how to use the map
		//show them the help frame
		else if (e.getSource() == helpButton) {
			helpFrame.setVisible(true);
		}

	}
}