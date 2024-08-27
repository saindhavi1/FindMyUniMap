package model;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {
	
	//This array list will hold all the programs
	ArrayList<Program> programArraylist = new ArrayList<>();


	public FileReader() {
			
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
	
	private String[] convList(String prerequisites) {
				
		String[] programArray = prerequisites.split("\\|",-2);

		return programArray;
	
		
	}

	public ArrayList<Program> getProgramArray() {
		return programArraylist;
		
	}
	
	

}
