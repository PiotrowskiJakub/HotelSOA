package pl.edu.agh.soa.ba.form;

import pl.edu.agh.soa.core.bean.Reservation;

public class InvoiceForm {
	private Reservation reservation;
	private String reservationID;

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public String getReservationID() {
		return reservationID;
	}

	public void setReservationID(String reservationID) {
		this.reservationID = reservationID;
	}

}
