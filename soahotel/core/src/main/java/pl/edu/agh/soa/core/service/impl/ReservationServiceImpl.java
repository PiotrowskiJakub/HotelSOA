package pl.edu.agh.soa.core.service.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.spi.ResolveResult;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import javafx.util.Pair;
import pl.edu.agh.soa.core.bean.Reservation;
import pl.edu.agh.soa.core.dao.ReservationDAO;
import pl.edu.agh.soa.core.service.ReservationService;

import java.util.Date;
import java.util.List;

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

	@Override
	public List<Pair<Date, Date>> getTermins(Integer year) {
		// TODO Auto-generated method stub
		return null;
	}

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

}
