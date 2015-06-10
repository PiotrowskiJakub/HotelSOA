package pl.edu.agh.soa.ba.form;

import pl.edu.agh.soa.core.bean.Address;
import pl.edu.agh.soa.core.bean.Contact;
import pl.edu.agh.soa.core.bean.Hotel;

public class CreateHotelForm {
	private Hotel hotel;
	
	
	
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
		return hotel.getAddress();
	}

	public Double getAverageRate() {
		return hotel.getAverageRate();
	}

	public Contact getContact() {
		return hotel.getContact();
	}

	public void setAddress(Address address) {
		hotel.setAddress(address);
	}

	public void setAverageRate(Double averageRate) {
		hotel.setAverageRate(averageRate);
	}

	public void setContact(Contact contact) {
		hotel.setContact(contact);
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
}
