package model;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MoreInfoProgram extends JFrame implements ActionListener {

	FontClassJeff fonts = new FontClassJeff();
	
	//Custom theme colours
	Color backgroundColour = new Color(255, 255, 255);
	Color programColour = new Color(57, 90, 154);
	
	
	JPanel container = new JPanel();
		JLabel reqProgTitle = new JLabel("Prerequisites: ");
		JLabel reqProgContent = new JLabel();
		
		JLabel reqGradeTitle = new JLabel("Grade Requirement: ");
		JLabel reqGradeContent = new JLabel();
		
		JLabel degreeTitle = new JLabel("Degree: ");
		JLabel degreeContent = new JLabel();
		
		
	JPanel titlePanel = new JPanel();
		JLabel title = new JLabel();
	
	JButton visitProgram = new JButton("View Program");
	JButton backButton = new JButton("Back");
	
	Program empty;

	
	public MoreInfoProgram(Program program) {
		
		empty = program;
				
		setLayout(null);	
		setSize(800,600);
		setVisible(true);
		getContentPane().setBackground(backgroundColour);
		
		addTitle(program);
		addBackButton();
		addInfo(program);
				
		
		
		
		
		
			
	}

	private void addInfo(Program program) {
		
		container.setBounds(50,140,650,380);
		container.setLayout(null);
		add(container);
		
		
		reqProgTitle.setBounds(50,30,200,50);
		reqProgTitle.setFont(FontClassJeff.medium25);
		reqProgTitle.setForeground(Color.black);	
		
		reqProgContent.setBounds(50,70,540,20);
		reqProgContent.setFont(FontClassJeff.regular16);
		reqProgContent.setForeground(Color.black);	
		
		String reqProgs = "";
		
		for(String prog : program.getPrerequisites())

			reqProgs = reqProgs + prog + ", " ;
		
		reqProgs = reqProgs.substring(0, reqProgs.length() - 2);
		
		reqProgContent.setText(reqProgs);

		
		
		reqGradeTitle.setBounds(50,110,200,50);
		reqGradeTitle.setFont(FontClassJeff.medium25);
		reqGradeTitle.setForeground(Color.black);	
		
		reqGradeContent.setBounds(50,150,400,20);
		reqGradeContent.setText(program.getGradeRequirement() + "%");
		reqGradeContent.setFont(FontClassJeff.regular16);
		reqGradeContent.setForeground(Color.black);	
		
		
		degreeTitle.setBounds(50,190,200,50);
		degreeTitle.setFont(FontClassJeff.medium25);
		degreeTitle.setForeground(Color.black);	
		
		degreeContent.setBounds(50,230,400,20);
		degreeContent.setText(program.getDegree());
		degreeContent.setFont(FontClassJeff.regular16);
		degreeContent.setForeground(Color.black);	
		
		
		visitProgram.setBounds(50,300,300,50);
		visitProgram.addActionListener(this);
		visitProgram.setFocusPainted(false);
		visitProgram.setForeground(Color.WHITE);
		visitProgram.setFont(FontClassJeff.medium25);  
		visitProgram.setBackground(programColour);
		
		
		
		container.add(visitProgram);
		container.add(reqProgTitle);
		container.add(reqProgContent);
		container.add(reqGradeTitle);
		container.add(reqGradeContent);
		container.add(degreeTitle);
		container.add(degreeContent);
	
		
		
		
		
	}

	private void addBackButton() {
		
		backButton.setBounds(50,30,150,50);
		backButton.addActionListener(this);
		backButton.setFocusPainted(false);
		backButton.setForeground(Color.WHITE);
		backButton.setFont(FontClassJeff.medium25);  
		backButton.setBackground(programColour);
		add(backButton);
		
	}

	private void addTitle(Program program) {

		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
		titlePanel.setBounds(50,90,700,40);
		titlePanel.setBackground(backgroundColour);

		title.setText("" + program.getName() + " - " + program.getUniversity());
		title.setFont(FontClassJeff.semibold30);
		title.setForeground(Color.black);
		title.setBounds(200,70,700,80);
		title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		titlePanel.add(title);
		
		add(titlePanel);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == backButton) {
			setVisible(false);
			dispose();
		}
		else if(e.getSource() == visitProgram) {
			try {
			    Desktop.getDesktop().browse(new URL(empty.getProgramLink()).toURI());
			} catch (Exception ex) {}
		}
		
	}


}
