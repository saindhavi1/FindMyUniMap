package view;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.*;
import javax.swing.border.Border;

import controller.FontClass;
import controller.FontClass;
import model.Program;
import model.ProgramPanel;
import model.ReadFile;
import model.User;

/*
 * 
 * https://stackoverflow.com/questions/3333623/how-to-get-rid-of-the-border-with-a-jtable-jscrollpane#:~:text=by%20using%3A,it%20works.
 * 
 */

public class GeneralSearchFrame extends JFrame{
		 
	
	private ReadFile importedPrograms = new ReadFile();
	

	private ProgramPanel[] programs = new ProgramPanel[importedPrograms.getProgramArray().size()];

	FontClass fonts = new FontClass();
	
	static User user;
	
	MenuBar menuBar;
	
	//Custom theme colours
	Color backgroundColour = new Color(255, 255, 255);
	Color programColour = new Color(57, 90, 154);
	
	//Border programBorder = BorderFactory.createLineBorder(scrollPaneColour, 10);

	//This panel contains the scrollpane, search, sort, and filter objects.
	JPanel containerPanel = new JPanel();
	
		//This panel contains the search, sort, and filter objects.
		JPanel containerTopPanel = new JPanel();
		
			//This panel contains the sort and filter objects
			JPanel sortAndFilterObjects = new JPanel(); 
				
				//Sort objects
				String[] sortOptionsList = { "Programs (A-Z)", "University (A-Z)",
						 "Grade Range (High - Low)" };
				JComboBox<String> sortOptions = new JComboBox<String>(sortOptionsList);
				
				//Filter objects
				JButton filterButton = new JButton("Filter Options");
			
			//This panel contains the search objects.
			JPanel searchObjects = new JPanel();
			
				//Search field
				JTextField searchField = new JTextField();
				
				//Search button
				JButton searchButton = new JButton(new ImageIcon("images/searchButton.png"));
		
		
		//This panel contains the scroll pane objects.	
		JScrollPane programsScrollPane = new JScrollPane();	
		
			JPanel programsPanel = new JPanel();
			
		//This panel makes the frame have more white space. 
		JPanel whiteSpace = new JPanel();
		
		

	
	public GeneralSearchFrame(User user) {
			
		
		this.user = user;
		
		//Customize the frame
		getContentPane().setBackground(backgroundColour);
		setLayout(null);	
		setLocationRelativeTo(null); //location of frame
		
		//Methods to add the top swing objects onto the frame
		addTopPanel();
		
		//Methods to add the bottom swing objects onto the frame
		addBottomPanel();
		
		menuBar = new MenuBar(user);
		add(menuBar);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280,720);
		setVisible(true);
		
	}

	private void addTopPanel() {
		
		//Sort and filter objects		
		sortAndFilterObjects.setLayout(null);
		
			sortOptions.setFocusable(false);
			filterButton.setFocusPainted(false);
	
			sortOptions.setForeground(programColour);
			filterButton.setForeground(Color.WHITE);
			
			sortOptions.setFont(fonts.medium20);
			filterButton.setFont(fonts.medium20);
			  
			//sortOptions.setBackground(programColour);
			filterButton.setBackground(programColour);
			
			sortOptions.setBounds(25, 30, 250, 50);
			filterButton.setBounds(300, 30, 200, 50);
		
			filterButton.setOpaque(true);
			sortOptions.setOpaque(true);

			filterButton.setBorder(BorderFactory.createEmptyBorder()); //create empty border
			
			sortAndFilterObjects.add(sortOptions);
			sortAndFilterObjects.add(filterButton);
		
	
		//Search objects
		searchObjects.setLayout(null);
		
			//Customize the search button
			searchButton.setOpaque(false);
			searchButton.setContentAreaFilled(false);
			searchButton.setBorderPainted(false);
			searchButton.setFocusPainted(false);
			
			searchField.setFont(fonts.medium20);
		
		
			searchButton.setBounds(50, 20, 60, 60);
			searchField.setBounds(120, 20, 380, 60);	
			
			searchObjects.add(searchField);
			searchObjects.add(searchButton);
		
			
		
		
		//Add all sub panels to the main panel.
		containerTopPanel.setLayout(new BoxLayout(containerTopPanel, BoxLayout.X_AXIS));
		containerTopPanel.add(sortAndFilterObjects);
		containerTopPanel.add(searchObjects);
		
		containerTopPanel.setBounds(100, 160, 1080, 100);
		add(containerTopPanel);
		
		
		
			
	}
	

	private void addBottomPanel() {
		
		
		programsScrollPane.setBounds(100,260,1080,360);
		programsScrollPane.setBorder(null);
		
		programsPanel.setLayout(new BoxLayout(programsPanel, BoxLayout.Y_AXIS));
		
	
					
		int num = 0;
			
		for(int i = 0; i < importedPrograms.getProgramArray().size(); i++ ) {
						
			ProgramPanel currentProgram = programs[i];
						
			currentProgram = new ProgramPanel(importedPrograms.getProgramArray().get(i).getName(),
					importedPrograms.getProgramArray().get(i).getUniversity(), false, importedPrograms.getProgramArray().get(i),
					importedPrograms.getProgramArray().get(i).getGradeRequirement());
			currentProgram.add(currentProgram.getProgramBackground());
			
			currentProgram.getProgramBackground().add(currentProgram.getProgramName());
			currentProgram.getProgramBackground().add(currentProgram.getUniversityName());
			currentProgram.getProgramBackground().add(currentProgram.getFavouriteButton());
			
			currentProgram.getFavouriteButton().setFocusPainted(false);
			currentProgram.getFavouriteButton().setContentAreaFilled(false);
			currentProgram.getFavouriteButton().setBorderPainted(false);
			
			currentProgram.getProgramName().setBorderPainted(false);
			currentProgram.getProgramName().setContentAreaFilled(false);
			currentProgram.getProgramName().setFocusPainted(false);
			currentProgram.getProgramName().setHorizontalAlignment(SwingConstants.LEFT);
			
			currentProgram.getUniversityName().setBorderPainted(false);
			currentProgram.getUniversityName().setContentAreaFilled(false);
			currentProgram.getUniversityName().setFocusPainted(false);
			currentProgram.getUniversityName().setHorizontalAlignment(SwingConstants.LEFT);

			
			programs[i] = currentProgram;
			programsPanel.add(currentProgram);
			
			num++;
			
			
		}
		
		programsScrollPane.setViewportView(programsPanel);
		add(programsScrollPane);
		
		whiteSpace.setBounds(100,620,1080,20);
		add(whiteSpace);
		

	}
	
	public static void main(String[] args) {
		new GeneralSearchFrame(user);
	}

	public ProgramPanel[] getPrograms() {
		return programs;
	}

	public void setPrograms(ProgramPanel[] programs) {
		this.programs = programs;
	}

	public FontClass getFonts() {
		return fonts;
	}

	public void setFonts(FontClass fonts) {
		this.fonts = fonts;
	}

	public Color getBackgroundColour() {
		return backgroundColour;
	}

	public void setBackgroundColour(Color backgroundColour) {
		this.backgroundColour = backgroundColour;
	}

	public Color getProgramColour() {
		return programColour;
	}

	public void setProgramColour(Color programColour) {
		this.programColour = programColour;
	}

	public JPanel getContainerPanel() {
		return containerPanel;
	}

	public void setContainerPanel(JPanel containerPanel) {
		this.containerPanel = containerPanel;
	}

	public JPanel getContainerTopPanel() {
		return containerTopPanel;
	}

	public void setContainerTopPanel(JPanel containerTopPanel) {
		this.containerTopPanel = containerTopPanel;
	}

	public JPanel getSortAndFilterObjects() {
		return sortAndFilterObjects;
	}

	public void setSortAndFilterObjects(JPanel sortAndFilterObjects) {
		this.sortAndFilterObjects = sortAndFilterObjects;
	}

	public String[] getSortOptionsList() {
		return sortOptionsList;
	}

	public void setSortOptionsList(String[] sortOptionsList) {
		this.sortOptionsList = sortOptionsList;
	}

	public JComboBox<String> getSortOptions() {
		return sortOptions;
	}

	public void setSortOptions(JComboBox<String> sortOptions) {
		this.sortOptions = sortOptions;
	}

	public JButton getFilterButton() {
		return filterButton;
	}

	public void setFilterButton(JButton filterButton) {
		this.filterButton = filterButton;
	}

	public JPanel getSearchObjects() {
		return searchObjects;
	}

	public void setSearchObjects(JPanel searchObjects) {
		this.searchObjects = searchObjects;
	}

	public JTextField getSearchField() {
		return searchField;
	}

	public void setSearchField(JTextField searchField) {
		this.searchField = searchField;
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	public void setSearchButton(JButton searchButton) {
		this.searchButton = searchButton;
	}

	public JScrollPane getProgramsScrollPane() {
		return programsScrollPane;
	}

	public void setProgramsScrollPane(JScrollPane programsScrollPane) {
		this.programsScrollPane = programsScrollPane;
	}

	public JPanel getProgramsPanel() {
		return programsPanel;
	}

	public void setProgramsPanel(JPanel programsPanel) {
		this.programsPanel = programsPanel;
	}
	
	public ReadFile getImportedPrograms() {
		return importedPrograms;
	}

	public void setImportedPrograms(ReadFile importedPrograms) {
		this.importedPrograms = importedPrograms;
	}
	
	
}


	