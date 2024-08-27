package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.ImageIcon;

import model.MoreInfoProgram;
import model.MoreInfoUniversity;
import model.ProgramPanel;
import model.User;
import view.GeneralSearchFrame;
import view.SortByFrame;

public class GeneralSearchController implements ActionListener {
	
	static int test;
	
	private GeneralSearchFrame frame;
	
	public GeneralSearchController(User user) {
		
		
		frame = new GeneralSearchFrame(user);
		
		//Method to add action listeners to all the GUI swing objects:
		addActionListeners();
		
	}

	private void addActionListeners() {
		
		//Add action listeners to the filter button, sort options, and the search bar
		getFrame().getFilterButton().addActionListener(this);
		getFrame().getSortOptions().addActionListener(this);
		getFrame().getSearchButton().addActionListener(this);
		
		
		
		//Add action listeners to all the program panels.
		for(int i = 0; i < getFrame().getImportedPrograms().getProgramArray().size(); i++) {
			
			ProgramPanel currentProgram = getFrame().getPrograms()[i];
			
			currentProgram.getFavouriteButton().addActionListener(this);
			currentProgram.getUniversityName().addActionListener(this);
			currentProgram.getProgramName().addActionListener(this);
		
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		
		if(event.getSource() == getFrame().getFilterButton()) {
			
			System.out.println("sort");
			new SortByFrame(this);
								
			
		}
		else if(event.getSource() == getFrame().getSearchButton()) {
			
			System.out.println("sort");
			updateFrame();
			
		}
		
		
		else if(event.getSource() == getFrame().getSortOptions()) {
			
			System.out.println("sort");
			
			String sortByOption = (String)getFrame().getSortOptions().getSelectedItem();
			
			if (sortByOption == "Programs (A-Z)") {
							
				Arrays.sort(getFrame().getPrograms(), Comparator.comparing(ProgramPanel::getProgName));
				
				updateFrame();
				
				
			
							
			}
			
			else if (sortByOption == "University (A-Z)") {
								
				Arrays.sort(getFrame().getPrograms(), Comparator.comparing(ProgramPanel::getUniName));
				
				updateFrame();
				
			
			}
			
			else if (sortByOption == "Grade Range (High - Low)") {
				
				Arrays.sort(getFrame().getPrograms(), Comparator.comparing(ProgramPanel::getGrade));
				
				updateFrame();
			}

		}
		
		//Handles any button click for a particular program.
		for(int i = 0; i < getFrame().getPrograms().length; i++) {
			
			
			ProgramPanel currentProgram = getFrame().getPrograms()[i];			
			
			
			//Checks if the favourite button was clicked:
			if (event.getSource() == currentProgram.getFavouriteButton()) {
				
				//Switch the state of the favourite option if it's clicked (true -> false, false -> true)
				currentProgram.setFavourite(!currentProgram.isFavourite());
				
				//Update the icon
				currentProgram.getFavouriteButton().setIcon(new ImageIcon("images/favourite" + currentProgram.isFavourite() + ".png"));
			
			}
			//Checks if the university name was clicked
			else if(event.getSource() == currentProgram.getUniversityName()) 
				
				new MoreInfoUniversity(getFrame().getPrograms()[i].getProgram());
								
	
			//Check if the program name was clicked
			else if(event.getSource() == currentProgram.getProgramName()) 
				
				new MoreInfoProgram(getFrame().getPrograms()[i].getProgram());				
				
			
		}
		
	}

	private void updateFrame() {
		
		//Remove all programs
		getFrame().getProgramsPanel().removeAll();
		
		//Get the text that's contained in the search field
		String search = getFrame().getSearchField().getText();
				
		//Check for all programs if they contain the text from the search field
		for(int i = 0; i < getFrame().getPrograms().length; i++) 
		
			//If either the program name or university matches what's in the search field, add that program to the panel
			if (getFrame().getPrograms()[i].getProgName().toLowerCase().contains(search.toLowerCase()) || 
					getFrame().getPrograms()[i].getUniName().toLowerCase().contains(search.toLowerCase())) 
				
				getFrame().getProgramsPanel().add(getFrame().getPrograms()[i]);	
				
			
		
		
		//Refresh the frame
		getFrame().repaint();
		getFrame().revalidate();
			
		
		
	}

	public GeneralSearchFrame getFrame() {
		return frame;
	}

	public void setFrame(GeneralSearchFrame frame) {
		this.frame = frame;
	}
	


}
