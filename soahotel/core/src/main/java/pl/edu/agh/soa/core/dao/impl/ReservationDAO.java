package pl.edu.agh.soa.core.dao.impl;

import java.util.List;

import pl.edu.agh.soa.core.bean.Reservation;

public interface ReservationDAO {

	Reservation getReservation(Long id);

	List<Reservation> getReservations(Long id);

	void addReservation(Reservation reservation);

	void removeReservation(Long id);

	void updateReservation(Reservation reservation);

	List<Reservation> getReservations();

}
