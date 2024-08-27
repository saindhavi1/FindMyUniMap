package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

import com.opencagedata.jopencage.JOpenCageGeocoder;
import com.opencagedata.jopencage.model.JOpenCageForwardRequest;
import com.opencagedata.jopencage.model.JOpenCageLatLng;
import com.opencagedata.jopencage.model.JOpenCageResponse;

import view.Map;

public class ConvertAddresstoCoor {
	
	public ConvertAddresstoCoor() {
		try {
			// Create a Scanner object to get the data from the txt file
			Scanner inputFile = new Scanner(new File("data/University.txt"));
			// Use the delimiter to split the data when there is an '*'
			inputFile.useDelimiter("\\*|\r\n");
			// Use a for loop to assign each property in the file to
			// a variable, then make a University object and put the object into
			// the object array

			for (int i = 0; i < Map.universityArray.length; i++) {
				String uniName = inputFile.next();
				String phoneNum = inputFile.next();
				String address = inputFile.next();
				String link = inputFile.nextLine().substring(1);
				System.out.println(address);
				// create coordinates based on the address
				JOpenCageLatLng coor = convertAddressToCoordinates(address);

				updateFile(coor, uniName, phoneNum, address, link);
			}
			// close the file
			inputFile.close();

		}
		// if the file is not found, display an error message
		catch (FileNotFoundException e) {
			System.out.println("File error.");
		}

	}

	//method that will convert the address from Megan's data scraping to longitude and latitude coordinates
	public JOpenCageLatLng convertAddressToCoordinates(String address) {
		// from: https://opencagedata.com/tutorials/geocode-in-java
		JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder("96277ef1eefd402d885b71ff62b12a95");

		// get the request for the coordinates based on the address
		JOpenCageForwardRequest request = new JOpenCageForwardRequest(address);

		request.setRestrictToCountryCode("ca"); // restrict results to a specific country

		// Actually geocode for the coordinates based on the address
		JOpenCageResponse response = jOpenCageGeocoder.forward(request);
		// Accept the first pair or coordinates it gives
		JOpenCageLatLng firstResultLatLng = response.getFirstPosition(); // get the coordinate pair of the first result

		//return the coordinates
		return firstResultLatLng;
		
	}

	//update the file with the university's information plus their coordinates
	public void updateFile(JOpenCageLatLng coor, String uniName, String phoneNum, String address, String link) {

		try {
			//create a filewriter object to the path of the file
			FileWriter fileWriter = new FileWriter("data/UniversityWithCoor.txt", true);

			//write the university's information and the coordinates
			fileWriter.write(uniName + "*" + address + "*" + phoneNum + "*" + link
					+ "*" + coor.getLat().toString() + "*" + coor.getLng().toString() + "\n");
			//close the file
			fileWriter.close();

		} catch (IOException ex) {
			System.out.print("Invalid Path");
		}
	}

	public static void main(String[] args) {
		new ConvertAddresstoCoor();
	}
}
