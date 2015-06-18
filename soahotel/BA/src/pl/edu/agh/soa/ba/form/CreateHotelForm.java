package pl.edu.agh.soa.ba.form;

import pl.edu.agh.soa.core.bean.Address;
import pl.edu.agh.soa.core.bean.Contact;
import pl.edu.agh.soa.core.bean.Hotel;

/**
 * @author Agnieszka Szczurek
 *
 */
public class CreateHotelForm {
	private Hotel hotel;
	private Address address;
	private Contact contact;
	
	private Long id;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public CreateHotelForm(){
		if(hotel == null)
			hotel = new Hotel();
		if(address == null)
			address = new Address();
		if(contact == null)
			contact = new Contact();
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

	public String getMail() {
		return contact.getMail();
	}

	public Boolean getMailPermission() {
		return contact.getMailPermission();
	}

	public String getPhone() {
		return contact.getPhone();
	}

	public void setMail(String mail) {
		contact.setMail(mail);
	}

	public void setPhone(String phone) {
		contact.setPhone(phone);
	}

	public String getDesc() {
		return hotel.getDesc();
	}

	public String getName() {
		return hotel.getName();
	}

	public void setDesc(String desc) {
		hotel.setDesc(desc);
	}

	public void setName(String name) {
		hotel.setName(name);
	}

	public Address getAddress() {
		return address;
	}

	public Double getAverageRate() {
		return hotel.getAverageRate();
	}

	public Contact getContact() {
		return contact;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setAverageRate(Double averageRate) {
		hotel.setAverageRate(averageRate);
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
}
