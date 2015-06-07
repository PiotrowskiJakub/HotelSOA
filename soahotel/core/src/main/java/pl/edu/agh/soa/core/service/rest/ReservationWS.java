package pl.edu.agh.soa.core.service.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pl.edu.agh.soa.core.bean.Reservation;
import pl.edu.agh.soa.core.service.ReservationService;

import java.util.List;

@Stateless
@Path("/reservation")
public class ReservationWS {


	@EJB
	ReservationService reservationService;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReservation(@QueryParam("id") Long id) {
		Reservation reservation = reservationService.getReservation(id);
		return Response.ok(reservation, MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("/client/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReservations(@QueryParam("id") Long id) {
		List<Reservation> reservationList = reservationService.getReservations(id);
		return Response.ok(reservationList,  MediaType.APPLICATION_JSON).build();
	}

	public Response getTermins(Integer year) {
		// TODO Auto-generated method stub
		return null;
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createReservation(Reservation reservation) {
		reservationService.createReservation(reservation);
		return Response.ok().build();
	}

	@PUT
	@Path("/")
	public Response updateReservation(Reservation reservation) {
		reservationService.updateReservation(reservation);
		return Response.ok().build();
	}

	@DELETE
	@Path("/{id}")
	public Response reservationDelete(@QueryParam("id") Long id) {
		reservationService.reservationDelete(id);
		return Response.ok().build();
	}
	
}
