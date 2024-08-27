/*
 * 
 * External Sources:
 * - https://stackoverflow.com/questions/30408174/jsoup-how-to-get-href (get href link)
 * - https://www.w3schools.com/java/java_regex.asp (pattern searcher)
 * - https://stackoverflow.com/questions/600733/using-java-to-find-substring-of-a-bigger-string-using-regular-expression (pattern searcher)
 */
package controller;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.HttpStatusException;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import model.User;


public class WebScrapping {

	//Initialize a list of eng, math, comp sci and science program list 
	public static List<String[]> engPrograms = new ArrayList<String[]>();
	public static List<String[]> mathPrograms = new ArrayList<String[]>();
	public static List<String[]> compSciPrograms = new ArrayList<String[]>();
	public static List<String[]> sciPrograms = new ArrayList<String[]>();
	
	public static String[][] upcomingEvents = new String[4][5];
	
	public static List<String[]> universitiesInfo = new ArrayList<String[]>();
	
	public final static String[] UNIVERSITIES = {"Brock","Guelph","McMaster","OCAD","Ontario Tech",
			"Queen's","Ryerson","Waterloo","Western","Laurier","Windsor","York", "Toronto"};
	
	public final static String[] universitiesURL = {"brock", "guelph","mcmaster","ocad-u","ontario-tech",
			"queens","toronto-metropolitan","waterloo","western","laurier-waterloo","laurier-brantford","windsor","york",
			"york-glendon","toronto-mississauga","toronto-st-george","toronto-scarborough"};
	
	public WebScrapping() throws HttpStatusException, IOException {
		
		
		//getDataUni();
		//getDataEvents();
		
		getDataProgram();
		
		addToFile("data/compSciPrograms.txt", compSciPrograms);
		addToFile("data/engPrograms.txt", engPrograms);
		addToFile("data/mathPrograms.txt", mathPrograms);
		addToFile("data/sciencePrograms.txt", sciPrograms);
	}
	public static void getDataUni() throws IOException, HttpStatusException {
		
		try {

			for (int i = 0; i < universitiesURL.length; i++) {
			
				//URL of to get the data from
				String url = "https://www.ontariouniversitiesinfo.ca/universities/"+universitiesURL[i];
				
				//create a JSON document of the data
				Document doc = Jsoup.connect(url).get();
				
				
				//get the name of the university
				Element university = doc.select("div.content-header h1").first();
				
				//get the phone number
				Element phoneNum = doc.select("ul.contact-list span").first();
				
				//get the location
				Element location = doc.select("div.location-address span").first();
				
				//get the link to the uni's website
				Element link = doc.getElementsByClass("visit-website offsite-noicon").first(); 
		
				String[] tempUni = {university.text(),phoneNum.text(),location.text(),link.attr("href")};
			
				//add the collected information to a 2D array
				universitiesInfo.add(tempUni);
	
				
				System.out.println(universitiesInfo.get(i)[3]);
			}
			
			
			addToFile("data/universities.txt", universitiesInfo);
		
		} catch (HttpStatusException e) {

		} catch (IOException e1){
			
		}
		
	}
	
	
	public static String[][] getDataEvents() throws IOException, HttpStatusException {
		
		try {

			//URL of to get the data from
			String url = "https://www.ontariouniversitiesinfo.ca/universities/events";
			
			//create a JSON document of the data
			Document doc = Jsoup.connect(url).get();
			
			//get the university name
			Elements universityName = doc.select("h3 a");
			
			//get the universiy name
			Elements eventName = doc.select("h2 a");
			
			//get the date of the event
			Elements date = doc.select("div.result-summary div");
			
			//get the date of the evnt
			Elements time = doc.select("div.result-summary p");
			
			String timeStr;
			String dateStr;
			
			int events = 0;
			
			
			//loop through all events
			for (int i = 0; i < universityName.size(); i++) {
	
				
				//set a variable to signal for a valid university
				boolean validUni = false;
				
				//check for the universities in southern ontario
				for (int j = 0; j < UNIVERSITIES.length; j++) {
				
					if (universityName.get(i).text().contains(UNIVERSITIES[j])) {
						validUni = true;
						break;
					}
				}
				
			
				//if there are not already four events saved
				if (events <= 3 && validUni) {
					
					if (time.get(i).text().equals("View event details")) 
						timeStr = "N/A";
					else 
						timeStr = time.get(i).text();
					
					if (date.get(i).text().contains("Multiple dates")) {
						dateStr = "Multiple dates";
						timeStr = "N/A";
					}
					else
						dateStr = date.get(i).text();
				
					upcomingEvents[events][0] = universityName.get(i).text(); //university name
					upcomingEvents[events][1] = eventName.get(i).text(); //event name
					upcomingEvents[events][2] = dateStr; //date
					upcomingEvents[events][3] = timeStr;
					upcomingEvents[events][4] = eventName.get(i).attr("href"); //link to even
				
					events++;
				}
				else if(events >= 4) {
					break;
				}
				
			}
			
			
		
		} catch (HttpStatusException e) {

		} catch (IOException e1){
			
		}
		
		return upcomingEvents;

	}
 	
	public static void getDataProgram() throws IOException, HttpStatusException {


		for (int num = 2000; num < 4000; num++) {

			try {

				String url = "https://www.ontariouniversitiesinfo.ca/programs/" + num;
				
				Document doc = Jsoup.connect(url).get();

				Elements dtTitle = doc.select("dt");
				
				Elements h4Title = doc.getElementsByTag("h4");
				
				Elements ddInfo = doc.select("dd");
				
				//get the div with the class name: template-heading 
				Element programClass = doc.getElementsByClass("template-heading").first(); 
				
				//get the div with the class name: tabbed-subsection-summary
				Element addressClass = doc.getElementsByClass("tabbed-subsection-summary").first(); 
				
				//get the link to the university program with the class name: program-apply offsite-noicon
				Element linkClass = doc.getElementsByClass("program-apply offsite-noicon").first(); 
				
				//get the link to the university program with the class name: program-apply offsite-noicon
				Elements ul = doc.select("div.tabbed-subsection ul");
				                    
				//attributes for each program
				String programName = null;
				String universityName = null;
				String gradeRange = null;
				String degree = null;
				String address = null;
				String programLink = null;
				String prerequisites = "";
				String programNum = Integer.toString(num);
				//double cost = 0;
						
			
				for (int index = 0; index < dtTitle.size(); index++) {
			
					String dt = dtTitle.get(index).toString();	

					//get the university name
					if (dt.contains("University")) {
				 	
						universityName = removeTags(ddInfo.get(index)); //remove the tags
					}
					
					//Get the grade range
					if (dt.contains("Grade Range")) {
 		
						//gradeRange = removeTags(ddInfo.get(index)); //remove the tags
						
						//read for any patterns of the a percentage number (e.g 90)
						Pattern pattern = Pattern.compile("[0-9]{2}");
						
						//check the text of the li for the pattern
						Matcher matcher = pattern.matcher(ddInfo.get(index).text());
						
						//while there is a match
						while (matcher.find()) {
							//add the matched string to the array list
							gradeRange = matcher.group(0);  //now contains course code
						
						}
					}
					//Get the degree
					if (dt.contains("Degree")) {
						
						String fullText = ddInfo.get(index).toString(); //full text with tags
						degree = fullText.substring(fullText.indexOf("n>") + 2, fullText.indexOf("</span>")); //remove the tags
					}
				}
				
				//get the program name
				programName = programClass.text();
				
				//Get the program link
				programLink = linkClass.attr("href");
				
				//get the address
				
				//System.out.println(addressClass.text());
				
				int index = addressClass.text().indexOf(" Email");
				
				if (index != -1)
					address = addressClass.text().substring(0, index); //get only the string of the address not the email
				
				boolean matchFound = false;
				
				//for the first or second appearance of ul
				for (int i = 0; i < 2; i++) {
					
					//Create a variable to hold all the li'ss
					Elements li = ul.get(i).select("li");
					
						//for every li in the li list
						for (int j = 0; j < li.size(); j++) {
							
							//read for any patterns of the course code XXX#X (e.g. ENG4U)
							Pattern pattern = Pattern.compile("[A-Z]{3}[0-9][A-Z]");
							
							//check the text of the li for the pattern
							Matcher matcher = pattern.matcher(li.get(j).text());
						
							
							//while there is a match
							while (matcher.find()) {
								
								//if a match is found set tru
								matchFound = true;
								
								//add the matched string to the arraylist
								prerequisites += (matcher.group(0) + "|");  // s now contains course code
							
							}
						
						}

				}
				
				//if no matches were found, the only pre req is ENG4U
				if (!matchFound) {
				//create a string of the matchs
					prerequisites += "ENG4U|"; 
					
				}
				
				//remove the extra | at the end of the string
				prerequisites = prerequisites.substring(0, prerequisites.length()-1);
				
		
				//create a signal variable to flag when there is a valid university
				boolean validUni = false;
				
				//check to see if the program searched is at a university in Southern Ontario
				for (int i = 0; i < UNIVERSITIES.length; i++) {
					
					if (universityName == null) {
						validUni = false;
						break;
					}
					//if the university contains any of the university list
					if (universityName.contains(UNIVERSITIES[i])) {
						validUni = true;
						break;
					}
				}
				
				
				
				if (validUni) {
					
					//ADD TO ENGINEERING FILE
					if (programName.indexOf("Engineering") != -1) {
						
						//store the information into a string array {program name, university name, grade range}
						String[] info = {programNum, programName, universityName, gradeRange, degree, address, programLink, prerequisites}; 
						if (hasEnoughInfo(info))
							engPrograms.add(info);
					}
					 //ADD TO SCIENCE FILE
					else if (programName.indexOf("Science") != -1 || degree.indexOf("Science") != -1) {
					
						//store the information into a string array {program name, university name, grade range}
						String[] info = {programNum, programName, universityName, gradeRange, degree, address, programLink, prerequisites}; 
						if (hasEnoughInfo(info))
							sciPrograms.add(info);
					}
						
					//ADD TO COMPUTER SCIENCE FILE
					if (programName.indexOf("Computer Science") != -1) {
						
						//store the information into a string array {program name, university name, grade range}
						String[] info = {programNum, programName, universityName, gradeRange, degree, address, programLink, prerequisites}; 
						if (hasEnoughInfo(info))
							compSciPrograms.add(info);
					}
					//ADD TO MATH FILE
					else if (degree.indexOf("Mathematics") != -1 || programName.indexOf("Math") != -1) {
					
						//store the information into a string array {program name, university name, grade range}
						String[] info = {programNum, programName, universityName, gradeRange, degree, address, programLink, prerequisites}; 
						if (hasEnoughInfo(info))
							mathPrograms.add(info);
					}
				
				}
				
				
			} catch (HttpStatusException e1) {


			} catch (IOException e){}
		}
	}
	
	public static boolean hasEnoughInfo(String[] info) {
		
		for (String s : info) {
			if (s == null)
				return false;
		}
		
		return true;
		
	}


	public static String removeTags(Element ddInfo) {
		
		//Initialize variables to remove <dt> and <dd>
		String croppedString  = "";
		int newLine = 0;

		//Initialize a counter to remove unnesscary new line and space
		int numberOnNewLine = 0;
		
		//loop through the length of the whole sting including the tags
		for (int ch = 0; ch < ddInfo.toString().length(); ch++ )  {
			//if the character is a new line, increment the line counter
			if (ddInfo.toString().charAt(ch) == '\n') {
				newLine++;
			}
			
			//only if it is on the 2nd line, add to the new string
			if (newLine == 1) {
				
				//increment the number of characters on the new line
				numberOnNewLine++;
				
				//if the number of the character of the new line is greater than 2...
				//add to the range only st
				if (numberOnNewLine > 2)
					croppedString += ddInfo.toString().charAt(ch);
			}
		}
		//return the cropped string
		return croppedString;
	}

	
	public static void main(String[] args) throws HttpStatusException, IOException {
		
		new WebScrapping();
		
		
	}
	
	public static void addToFile(String fileName, List<String[]> programList) {
		
		FileWriter fr = null;
		try {
			fr = new FileWriter(fileName, true);
			
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		BufferedWriter br = new BufferedWriter(fr);
		try {
		
			//loop through all the programs for each field
			for (int i = 0; i < programList.size(); i++) {
				
				br.write("\r\n");
				//loop through the different number of data types for each program
				for (int j = 0; j < programList.get(i).length; j++) {
					
					//append to the text file
					br.write(programList.get(i)[j]);
					
					//if it is not on the last data, add a comma to separate the data
					if (j != programList.get(i).length-1)
						br.write("*");
				}
			}
			
			br.close();
			fr.close();
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}

}