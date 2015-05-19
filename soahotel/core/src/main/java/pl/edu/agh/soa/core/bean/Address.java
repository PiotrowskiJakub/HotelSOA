package pl.edu.agh.soa.core.bean;

/**
 * @author Piotr Konsek
 * 
 * class holds all address data
 */
public class Address {
	
	private String street;
	private Integer houseNumber;
	private Integer flatNumber;
	private String postalCode;
	private String city;
	private String state;
	private String country;
	private Double latitude;
	private Double longitude;
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public Integer getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(Integer houseNumber) {
		this.houseNumber = houseNumber;
	}
	public Integer getFlatNumber() {
		return flatNumber;
	}
	public void setFlatNumber(Integer flatNumber) {
		this.flatNumber = flatNumber;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
}
