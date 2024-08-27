package model;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*This class creates programs JPanels that will be displayed in the GeneralSearch class.

*/
 public class ProgramPanel extends JPanel {
	
	//Preset customization for the JPanel
	FontClassJeff fonts = new FontClassJeff();	
	Color programColour = new Color(57, 90, 154);
	JLabel programBackground = new JLabel(new ImageIcon("images/programPanelBackground.png"));

	//Fields
	private int programNum;
	private JButton programName;
	private JButton universityName;
	private String uniName;
	private String progName;
	private String grade;
	private JButton favouriteButton;
	private Program program;

	private boolean favourite;

	//Constructor class 
	public ProgramPanel(String programName, String universityName, boolean favourite, Program program, String grade) {
		
		this.programName = new JButton("" + programName);
		this.programName.setFont(FontClassJeff.programFont);
		this.programName.setForeground(programColour);
		this.programName.setBounds(70,10,800,50);
		
		this.universityName = new JButton("" + universityName);
		this.universityName.setFont(FontClassJeff.medium25);
		this.universityName.setBounds(70,45,300,50);
		
		this.favourite = favourite;
		
		this.uniName = universityName;
		this.progName = programName;
		this.grade = grade;
		
		this.setProgram(program);
		
		this.favouriteButton = new JButton(new ImageIcon("images/favourite" + favourite + ".png"));
		this.favouriteButton.setBounds(900,30,60,60);
		
		
	}

	
	public JLabel getProgramBackground() {
		return programBackground;
	}
	

	public int getProgramNum() {
		return programNum;
	}

	public void setProgramNum(int programNum) {
		
		this.programNum = programNum;
	}

	public JButton getProgramName() {
		return programName;
	}

	public void setProgramName(JButton programName) {
		
		this.programName = programName;
	}
		

	public JButton getUniversityName() {
		return universityName;
	}

	public void setUniversityName(JButton universityName) {
		
		this.universityName = universityName;		
	
	}

	public JButton getFavouriteButton() {
		return favouriteButton;
	}

	public void setFavouriteButton(JButton favourite) {
		
		this.favouriteButton = favourite;
		
	}

	public boolean isFavourite() {
		return favourite;
	}

	public void setFavourite(boolean favourite) {
		this.favourite = favourite;
	}

	public String getUniName() {
		return uniName;
	}

	public void setUniName(String uniName) {
		this.uniName = uniName;
	}		
	
	public String getProgName() {
		return progName;
	}

	public void setProgName(String progName) {
		this.progName = progName;
	}
	
	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}


	public String getGrade() {
		return grade;
	}


	public void setGrade(String grade) {
		this.grade = grade;
	}
	
}