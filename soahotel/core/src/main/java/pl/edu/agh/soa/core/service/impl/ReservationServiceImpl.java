package pl.edu.agh.soa.core.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pl.edu.agh.soa.core.bean.Reservation;
import pl.edu.agh.soa.core.dao.ReservationDAO;
import pl.edu.agh.soa.core.service.ReservationService;

/**
 * @author Agnieszka Szczurek
 *
 */
@Stateless
public class ReservationServiceImpl implements ReservationService {

	@EJB
	private ReservationDAO reservationDAO;
	
	@Override
	public Reservation getReservation(Long id) {

		return this.reservationDAO.getReservation(id);
	}

	@Override
	public List<Reservation> getReservations(Long id) {

		return this.reservationDAO.getReservations(id);
	}

//	@Override
//	public List<Pair<Date, Date>> getTermins(Integer year) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public void createReservation(Reservation reservation) {
		this.reservationDAO.addReservation(reservation);
	}

	@Override
	public void updateReservation(Reservation reservation) {
		this.reservationDAO.updateReservation(reservation);

	}

	@Override
	public void reservationDelete(Long id) {
		this.reservationDAO.removeReservation(id);

	}

	@Override
	public List<Reservation> getReservations() {
		return reservationDAO.getReservations();
	}

	@Override
	public List<Reservation> getReservations(Long hotelID, Long roomTypeID,
			Integer year) {
		return reservationDAO.getReservations(hotelID, roomTypeID, year);
	}

	@Override
	public List<Reservation> getHotelReservations(Long id) {
		return reservationDAO.getHotelReservations(id);
	}

}
