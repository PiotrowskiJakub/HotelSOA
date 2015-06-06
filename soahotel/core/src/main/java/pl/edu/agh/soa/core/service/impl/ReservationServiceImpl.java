package pl.edu.agh.soa.core.service.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import pl.edu.agh.soa.core.bean.Reservation;
import pl.edu.agh.soa.core.dao.ReservationDAO;
import pl.edu.agh.soa.core.service.ReservationService;

/**
 * @author Piotr Konsek
 *
 */
@Stateless
public class ReservationServiceImpl implements ReservationService {

	@EJB
	private ReservationDAO reservationDAO;
	
	@Override
	public Response getReservation(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getReservations(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getTermins(Integer year) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@POST
	@Path("reservation")
	public Response createReservation(Reservation reservation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response updateReservation(Reservation reservation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response reservationDelete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
