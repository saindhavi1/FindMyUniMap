

import java.util.Arrays;
import java.util.List;

public class Program {

	//Instance variables
	String name;
	String university;
	String address;
	String degree;
	int programNum;

	String programLink;
	String gradeRequirement;
	String[] prerequisites;
	
	double cost;
	
	//Constructor
	public Program(int programNum, String name, String university, String gradeRequirement, String degree, String address,
			String programLink, String[] courses) {
		super();
		this.programNum = programNum;
		this.name = name;
		this.university = university;
		this.address = address;
		this.programLink = programLink;
		this.degree = degree;
		this.gradeRequirement = gradeRequirement;
		this.prerequisites = courses;
	}

	//GETTERS AND SETTERS
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getProgramLink() {
		return programLink;
	}

	public void setProgramLink(String programLink) {
		this.programLink = programLink;
	}

	public String getGradeRequirement() {
		return gradeRequirement;
	}

	public void setGradeRequirement(String gradeRequirement) {
		this.gradeRequirement = gradeRequirement;
	}

	public String[] getPrerequisites() {
		return prerequisites;
	}

	public void setPrerequisites(String[] prerequisites) {
		this.prerequisites = prerequisites;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public int getProgramNum() {
		return programNum;
	}

	public void setProgramNum(int programNum) {
		this.programNum = programNum;
	}

	
	
	
	
	
}