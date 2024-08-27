package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFile {
	
	//This array list will hold all the programs
	ArrayList<Program> programArraylist = new ArrayList<>();


	public ReadFile() {
			
		try {

			Scanner programs = new Scanner(new File("data/allPrograms"));
			
			programs.useDelimiter("\\*|\r\n");
												
			// Iterate through all programs 
			for (int i = 0; i < 305; i++) {
				
				int programNum = programs.nextInt();
				String programName = programs.next();
				String universityName = programs.next();
				String gradeRange = programs.next();
				String degree = programs.next();
				String address = programs.next();
				String programLink = programs.next();
				String prerequisites = programs.next();
				String[] courses = convList(prerequisites);
				
							
		
				// Add the created programs to the program array
				programArraylist.add(new Program(programNum, programName, universityName, gradeRange, degree, address, programLink, courses));
			}
			
		} catch (FileNotFoundException e) { // for errors or if there is no input
			System.out.println("File Error");
		}		
		
	}
	
	public static int numLines(String fileName) {
	
		//get the number of lines in the text file
		//initialize the number of lines
	    int lines = 0;
	    
		//check how many scores are in the accounts file
		File file = new File(fileName);
		
		//create a new line number reader for the txt file
	    try (LineNumberReader lnr = new LineNumberReader(new FileReader(file))) {

	    	//while the line is not empty
	    	while (lnr.readLine() != null) ;

	    	//the number of lines becomes the line number
	    	lines = lnr.getLineNumber();

	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	    
	   return lines;
	}
	
	public static boolean checkAlreadyExists(String username) {
		
		int lines = numLines("data/accounts.txt");
		
		//read the file of previously created login info
		try {
			// Create Scanner object to get the file
			Scanner inputFile = new Scanner(new File("data/accounts.txt"));
			
			for (int i = 0; i < lines; i++) {
				
				//set a delimiter which will stop scanning the data for one row of progress
				inputFile.useDelimiter(",|\r\n");
				
				//get the username and password in the file and turn it into a byte array
				String usernameStr = inputFile.next();
				String passwordStr = inputFile.next();
				String firstName = inputFile.next();
				String lastName = inputFile.next();
				String emailAddress = inputFile.next();
				String streetNum = inputFile.next();
				String street = inputFile.next();
				String city = inputFile.next();
				String postalCode = inputFile.next();
				String fieldOfStudy = inputFile.next();
				
				if (usernameStr.equals(username)) {
					return true;
				}
			}
		
			inputFile.close(); //close the scanner 
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		
		return false;
	}
	
	private String[] convList(String prerequisites) {
				
		String[] programArray = prerequisites.split("\\|",-2);

		return programArray;
	
		
	}

	public ArrayList<Program> getProgramArray() {
		return programArraylist;
		
	}
	
	

}
