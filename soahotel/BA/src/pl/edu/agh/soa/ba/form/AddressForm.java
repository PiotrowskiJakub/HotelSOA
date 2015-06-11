package pl.edu.agh.soa.ba.form;

import pl.edu.agh.soa.core.bean.Address;

public class AddressForm {
	private Address address;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getCity() {
		return address.getCity();
	}

	public String getCountry() {
		return address.getCountry();
	}

	public Integer getFlatNumber() {
		return address.getFlatNumber();
	}

	public String getHouseNumber() {
		return address.getHouseNumber();
	}

	public Double getLatitude() {
		return address.getLatitude();
	}

	public Double getLongitude() {
		return address.getLongitude();
	}

	public String getPostalCode() {
		return address.getPostalCode();
	}

	public String getState() {
		return address.getState();
	}

	public String getStreet() {
		return address.getStreet();
	}

	public void setCity(String city) {
		address.setCity(city);
	}

	public void setCountry(String country) {
		address.setCountry(country);
	}

	public void setFlatNumber(Integer flatNumber) {
		address.setFlatNumber(flatNumber);
	}

	public void setHouseNumber(String houseNumber) {
		address.setHouseNumber(houseNumber);
	}

	public void setLatitude(Double latitude) {
		address.setLatitude(latitude);
	}

	public void setLongitude(Double longitude) {
		address.setLongitude(longitude);
	}

	public void setPostalCode(String postalCode) {
		address.setPostalCode(postalCode);
	}

	public void setState(String state) {
		address.setState(state);
	}

	public void setStreet(String street) {
		address.setStreet(street);
	}
	
	
}
