package pl.edu.agh.soa.ba.form;

import pl.edu.agh.soa.core.bean.Hotel;

public class ReportForm {
	private Hotel hotel;
	private String hotelID;

	public ReportForm(){
		hotel = new Hotel();
	}
	
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public String getHotelID() {
		return hotelID;
	}

	public void setHotelID(String hotelID) {
		this.hotelID = hotelID;
	}
}
