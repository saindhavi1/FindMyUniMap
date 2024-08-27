package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import controller.GeneralSearchController;
import model.FontClassJeff;
import model.ProgramPanel;

public class SortByFrame extends JFrame implements ActionListener {

	ArrayList<String> values = new ArrayList<>();

	FontClassJeff fonts = new FontClassJeff();

	Border border = BorderFactory.createLineBorder(Color.white, 8);

	String[] universityList = { "Ontario Tech University", "Queen's University", "Brock University",
			"McMaster University", "Sir Wilfrid Laurier University", "Toronto Metropolitan University",
			"University of Guelph", "University of Toronto", "University of Waterloo", "University of Windsor",
			"Western University", "York University", "Trent University" };

	// Kinesiology + Nursing + Biochemistry + Biomedical +
	String[] programList = { "Engineering", "Computer Science", "Medical Sciences", "Earth Sciences", "Mathematic",
			"Biology", "Chemistry", "Physics" };

	// Custom theme colours
	Color backgroundColour = new Color(255, 255, 255);
	Color programColour = new Color(57, 90, 154);

	JPanel filterOptions = new JPanel();

	JPanel universityOptions = new JPanel();
	JLabel universityLabel = new JLabel("University");
	JCheckBox[] universities = new JCheckBox[universityList.length];

	JPanel programOptions = new JPanel();
	JLabel programLabel = new JLabel("Program");
	JCheckBox[] programs = new JCheckBox[programList.length];

	JPanel gradeOptions = new JPanel();
	JLabel gradeLabel = new JLabel("Grade");
	JCheckBox[] grades = new JCheckBox[5];

	JButton save = new JButton("Save");
	JButton switchValues = new JButton("All Off");
	boolean value = true;

	GeneralSearchController empty;

	public SortByFrame(GeneralSearchController parent) {

		empty = parent;

		getContentPane().setBackground(backgroundColour);

		setLayout(null);

		filterOptions.setLayout(null);
		filterOptions.setBounds(80, 50, 900, 550);
		add(filterOptions);

		addUniversity();
		addPrograms();
		addGrades();

		save.setBounds(720, 475, 130, 50);
		save.addActionListener(this);
		save.setFocusPainted(false);
		save.setForeground(Color.WHITE);
		save.setFont(FontClassJeff.medium25);
		save.setBackground(programColour);

		switchValues.setBounds(50, 475, 200, 50);
		switchValues.addActionListener(this);
		switchValues.setFocusPainted(false);
		switchValues.setFont(FontClassJeff.medium25);
		switchValues.setBackground(Color.black);
		switchValues.setForeground(programColour);

		filterOptions.add(switchValues);
		filterOptions.add(save);

		setSize(1100, 650);
		setVisible(true);

	}

	private void addUniversity() {

		universityOptions.setLayout(new BoxLayout(universityOptions, BoxLayout.Y_AXIS));
		universityLabel.setFont(FontClassJeff.medium25);
		universityLabel.setForeground(programColour);
		universityOptions.add(universityLabel);
		universityOptions.setBackground(Color.white);
		universityOptions.setBorder(border);
		universityOptions.setBounds(50, 50, 300, 400);

		int index = 0;

		for (String university : universityList) {

//			if(values.contains(university)) {
//				universities[index] = new JCheckBox(university, true);
//			}
//			else {
//				universities[index] = new JCheckBox(university, false);
//			}

			universities[index] = new JCheckBox(university, false);
			universities[index].setFont(FontClassJeff.regular16);
			universities[index].setAlignmentX(Component.LEFT_ALIGNMENT);
			universities[index].setContentAreaFilled(false);
			universities[index].setFocusPainted(false);
			universities[index].setBorderPainted(false);
			universities[index].addActionListener(this);
			universityOptions.add(universities[index]);

			index++;
		}

		filterOptions.add(universityOptions);

	}

	private void addPrograms() {

		programOptions.setLayout(new BoxLayout(programOptions, BoxLayout.Y_AXIS));

		programLabel.setFont(FontClassJeff.medium25);
		programLabel.setForeground(programColour);
		programOptions.add(programLabel);
		programOptions.setBorder(border);
		programOptions.setBounds(400, 50, 200, 400);
		programOptions.setBackground(Color.white);

		int index = 0;

		for (String program : programList) {

			programs[index] = new JCheckBox(program, false);
			programs[index].setFont(FontClassJeff.regular16);
			programs[index].setAlignmentX(Component.LEFT_ALIGNMENT);
			programs[index].setContentAreaFilled(false);
			programs[index].setFocusPainted(false);
			programs[index].setBorderPainted(false);
			programs[index].addActionListener(this);

			programOptions.add(programs[index]);
			index++;

		}

		filterOptions.add(programOptions);

	}

	private void addGrades() {
		gradeOptions.setBounds(650, 50, 200, 400);
		gradeOptions.setLayout(new BoxLayout(gradeOptions, BoxLayout.Y_AXIS));
		gradeLabel.setFont(FontClassJeff.medium25);
		gradeLabel.setForeground(programColour);
		gradeOptions.add(gradeLabel);
		gradeOptions.setBorder(border);

		gradeOptions.setBackground(Color.white);
		filterOptions.add(gradeOptions);

		int counter = 0;

		// Loop for numbers 70 to 90, skipping 5 each time (for all programs the lowest
		// they take is 70,
		// and the highest is 90, so only need to use grade ranges between there.
		for (int index = 70; index <= 85; index += 5) {

			grades[counter] = new JCheckBox(index + "% - " + (index + 4) + "%", false);
			grades[counter].setFont(FontClassJeff.regular16);
			grades[counter].setAlignmentX(Component.LEFT_ALIGNMENT);
			grades[counter].setContentAreaFilled(false);
			grades[counter].setFocusPainted(false);
			grades[counter].setBorderPainted(false);
			grades[counter].addActionListener(this);
			gradeOptions.add(grades[counter]);
			gradeOptions.add(Box.createRigidArea(new Dimension(0, 10)));
			counter++;

			if (index == 85) {
				grades[counter] = new JCheckBox(90 + "%+", false);
				grades[counter].setFont(FontClassJeff.regular16);
				grades[counter].setAlignmentX(Component.LEFT_ALIGNMENT);
				grades[counter].setContentAreaFilled(false);
				grades[counter].setFocusPainted(false);
				grades[counter].setBorderPainted(false);
				grades[counter].addActionListener(this);
				gradeOptions.add(grades[counter]);

			}

		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == save) {

			for (JCheckBox currentBox : universities) {
				if (currentBox.isSelected())
					values.add(currentBox.getText());
				else
					values.remove(currentBox.getText());
			}

			for (JCheckBox currentBox : programs) {
				if (currentBox.isSelected())
					values.add(currentBox.getText());
				else
					values.remove(currentBox.getText());
			}

			for (JCheckBox currentBox : grades) {
				if (currentBox.isSelected())
					values.add(currentBox.getText());
				else
					values.remove(currentBox.getText());
			}

			for (String string : values)
				System.out.println(string);

			empty.getFrame().getProgramsPanel().removeAll();

			addNewPrograms();

			dispose();
			setVisible(false);

		} else if (e.getSource() == switchValues) {

			// If value is false then:
			if (value) {

				switchValues.setText("All On");
				switchValues.setBackground(programColour);
				switchValues.setForeground(Color.white);

			}

			// Otherwise (has to be true) then:
			else {

				switchValues.setText("All Off");
				switchValues.setBackground(Color.white);
				switchValues.setForeground(Color.black);

			}

			// Swap the value
			value = !value;

			// Update all the checkboxes to the current value
			for (JCheckBox currentBox : universities)
				currentBox.setSelected(value);

			for (JCheckBox currentBox : programs)
				currentBox.setSelected(value);

			for (JCheckBox currentBox : grades)
				currentBox.setSelected(value);

		}

	}

	public void addNewPrograms() {

		int num = 0;

		for (ProgramPanel currentPrograms : empty.getFrame().getPrograms()) {

			for (String string : values) {

				if (currentPrograms.getProgName().contains(string) || (currentPrograms.getUniName().contains(string))) {

					empty.getFrame().getProgramsPanel().add(currentPrograms);
					num++;
					break;
				}

			}

		}

		System.out.println(num);
		empty.getFrame().revalidate();
		empty.getFrame().repaint();

	}

}
