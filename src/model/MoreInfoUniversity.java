package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MoreInfoUniversity extends JFrame implements ActionListener {

	public MoreInfoUniversity(Program program) {
				
		setLayout(null);	
		setSize(1000,600);
		setVisible(true);
		
		JLabel test = new JLabel("" + program.getUniversity());
		test.setBounds(100,100,100,100);
		add(test);
			
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		
	}

}
