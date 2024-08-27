import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//This class will go through Megan's web scraping and get the university's name, 
//address, website and phone number. It will then convert the address into coordinates
//and write the university's information into a file.
//This makes it easier for the program to run faster.
public class CreateUniFile {
	public CreateUniFile() {
		try {
			// Create a Scanner object to get the data from the txt file
			Scanner inputFile = new Scanner(new File("data/allprograms.txt"));
			// Use the delimiter to split the data when there is a comma
			inputFile.useDelimiter("\\*|\r\n");
			// Use a for loop to assign each property in the file to
			// a variable, then make a University object and put the object into
			// the object array

			while(inputFile.hasNext()) {
				inputFile.next();
				String uniName = inputFile.next();
				inputFile.next();
				inputFile.next();
				String address = inputFile.next();
				String link = inputFile.next();
				inputFile.next();
//				String name = inputFile.next();
//				String address = inputFile.next();
//				String phoneNumber = inputFile.next();
//				String link = inputFile.nextLine().substring(1);
//
//				Map.universityArray[i] = new University(name, address, phoneNumber, link);
				
				System.out.println(uniName + " " + address + " " + link);
			}
			// close the file
			inputFile.close();
		}
		// if the file is not found, display an error message
		catch (FileNotFoundException e) {
			System.out.println("File error.");
		}
		
		
//		int j = 0;
//		//create a string array for both the names and their scores
//		String[] names = new String[5];
//		String[] scores = new String[5];
//		
//		//get the txt file so that the program can write in it
//		Path path = Paths.get("Level" + level + "LeaderBoard.txt");
//
//		try {
//			for (int i = 0; i<6; i++) {
//				//if the player's time is greater than 0 and the user entered a name
//				if (players[i].getTime() > 0 && !(players[i].getName() == "")) {
//					//then fill the arrays 
//					names[j] = players[i].getName();
//					scores[j] = String.valueOf(players[i].getTime());
//					j++;
//					//This will make sure that we only include the top 5
//					if (j == 5) {
//						break;
//					}
//				}
//			}
//			
//		//write the new leaderboard into the txt file
//		Files.writeString(path, names[0] + "," + scores[0] + "\n" + names[1] 
//				+ "," + scores[1] + "\n" + names[2] + "," + scores[2] 
//				+ "\n" + names[3] + "," + scores[3] + "\n" + names[4] 
//				+ "," + scores[4], StandardCharsets.UTF_8);
//
//		} catch(IOException ex) {
//			System.out.print("Invalid Path");
//		}
	}
	
	public static void main(String[] args) {
		new CreateUniFile();
	}
}
