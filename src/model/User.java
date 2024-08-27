package model;

import java.util.List;


public class User {

	//Instance variables
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String numAddress;
	private String street;
	private String city;
	private String postalCode;
	private String fieldOfStudy;
	private List<String[]> marks;
	private List<String> savedPrograms;
	
	

	public User(String userName, String password, String firstName, String lastName, String emailAddress,
			String numAddress, String street, String city, String postalCode, String fieldOfStudy) {
		super();
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.numAddress = numAddress;
		this.street = street;
		this.city = city;
		this.postalCode = postalCode;
		this.fieldOfStudy = fieldOfStudy;
	}

	//GETTERS AND SETTERS

	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getEmailAddress() {
		return emailAddress;
	}



	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}



	public String getNumAddress() {
		return numAddress;
	}



	public void setNumAddress(String numAddress) {
		this.numAddress = numAddress;
	}



	public String getStreet() {
		return street;
	}



	public void setStreet(String street) {
		this.street = street;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getPostalCode() {
		return postalCode;
	}



	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}



	public String getFieldOfStudy() {
		return fieldOfStudy;
	}



	public void setFieldOfStudy(String fieldOfStudy) {
		this.fieldOfStudy = fieldOfStudy;
	}



	public List<String[]> getMarks() {
		return marks;
	}



	public void setMarks(List<String[]> marks) {
		this.marks = marks;
	}



	public List<String> getSavedPrograms() {
		return savedPrograms;
	}



	public void setSavedPrograms(List<String> savedPrograms) {
		this.savedPrograms = savedPrograms;
	}

	
	
	
	
}
