package pl.edu.agh.soa.core.service;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ws.rs.core.Response;

import javafx.util.Pair;
import pl.edu.agh.soa.core.bean.Reservation;
import pl.edu.agh.soa.core.dao.ReservationDAO;

import java.util.Date;
import java.util.List;

@Remote
public interface ReservationService {
	
	//GET
	/**
	 * @param id
	 * @return 200|401|404 reservation with given id
	 */
	public Reservation getReservation(Long id);
		
	/**
	 * @param id
	 * @return 200|401|404 list of reservation from client with given id 
	 */
	public List<Reservation> getReservations(Long id);
	
	/**
	 * @param year
	 * @return 200 and list of free termins
	 */
	//public List<Pair<Date,Date>> getTermins(Integer year);
		
	//POST
	/**
	 * @param reservation
	 * @return 201|401
	 */
	public void createReservation(Reservation reservation);
	
	//PUT
	/**
	 * @param reservation
	 * @return 200|401|403
	 */
	public void updateReservation(Reservation reservation);
	
	//DELETE
	/**
	 * @param id
	 * @return 200|404
	 */
	public void reservationDelete(Long id);
	
}
