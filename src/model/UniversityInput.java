package model;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.jxmapviewer.viewer.GeoPosition;

import view.Map;

public class UniversityInput {

	// This method will fill the University Array with all the universities
	// from the updated text file with the university's coordinates

	public UniversityInput() {
		try {
			// Create a Scanner object to get the data from the txt file
			Scanner inputFile = new Scanner(new File("data/UniversityWithCoor.txt"));
			// Use the delimiter to split the data when there is a comma
			inputFile.useDelimiter("\\*|\r\n");
			// Use a for loop to assign each property in the file to
			// a variable, then make a University object and put the object into
			// the object array

			for (int i = 0; i < Map.universityArray.length; i++) {
				String name = inputFile.next();
				String address = inputFile.next();
				String phoneNumber = inputFile.next();
				String link = inputFile.next();
				String latitude = inputFile.next();
				String longitude = inputFile.nextLine().substring(1);

				Map.universityArray[i] = new University(name, address, phoneNumber, link, new GeoPosition(Double.parseDouble(latitude), Double.parseDouble(longitude)));
			}
			// close the file
			inputFile.close();
		}
		// if the file is not found, display an error message
		catch (FileNotFoundException e) {
			System.out.println("File error.");
		}
	}
}
