package pl.edu.agh.soa.core.service.rest;

import javax.ejb.Stateless;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import pl.edu.agh.soa.core.bean.Reservation;
import pl.edu.agh.soa.core.service.ReservationService;

@Stateless
@Path("/reservation")
public class ReservationWS implements ReservationService {

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
