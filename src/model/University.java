package model;
import org.jxmapviewer.viewer.GeoPosition;


//This class will create university objects that will be used in the Map
public class University {

	// fields
	private String name;
	private String address;
	private String phoneNumber;
	private String link;

	private GeoPosition coordinates;

	// constructor
	public University(String name, String address, String phoneNumber, String link, GeoPosition coordinates) {
		super();
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.link = link;
		this.coordinates = coordinates;
	}

	// getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public GeoPosition getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(GeoPosition coordinates) {
		this.coordinates = coordinates;
	}

	// toString method
	@Override
	public String toString() {
		return "University [name=" + name + ", address=" + address + ", phoneNumber=" + phoneNumber + ", link=" + link
				+ ", coordinates=" + coordinates + "]";
	}

	
	

}
