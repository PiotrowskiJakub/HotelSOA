package pl.edu.agh.soa.core.service;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ws.rs.core.Response;

import pl.edu.agh.soa.core.bean.Reservation;
import pl.edu.agh.soa.core.dao.ReservationDAO;

@Remote
public interface ReservationService {
	
	//GET
	/**
	 * @param id
	 * @return 200|401|404 reservation with given id
	 */
	public Response getReservation(Long id);
		
	/**
	 * @param id
	 * @return 200|401|404 list of reservation from client with given id 
	 */
	public Response getReservations(Long id);
	
	/**
	 * @param year
	 * @return 200 and list of free termins
	 */
	public Response getTermins(Integer year);
		
	//POST
	/**
	 * @param reservation
	 * @return 201|401
	 */
	public Response createReservation(Reservation reservation);
	
	//PUT
	/**
	 * @param reservation
	 * @return 200|401|403
	 */
	public Response updateReservation(Reservation reservation);
	
	//DELETE
	/**
	 * @param id
	 * @return 200|404
	 */
	public Response reservationDelete(Long id);
	
}
