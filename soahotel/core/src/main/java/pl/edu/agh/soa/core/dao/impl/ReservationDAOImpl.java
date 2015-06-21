package pl.edu.agh.soa.core.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import pl.edu.agh.soa.core.bean.Reservation;
import pl.edu.agh.soa.core.dao.AbstractDAO;
import pl.edu.agh.soa.core.dao.ReservationDAO;
import pl.edu.agh.soa.core.dict.AccountType;

/**
 * @author Agnieszka Szczurek
 *
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ReservationDAOImpl extends AbstractDAO implements ReservationDAO {

	@PersistenceContext(unitName = "soahoteldb")
	EntityManager em;

	public ReservationDAOImpl() {
		super();
		logger = Logger.getLogger(AccountDAOImpl.class);
	}

	@Override
	public Reservation getReservation(Long id) {
		return (Reservation) em.find(Reservation.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reservation> getReservations(Long id) {
		Session session = (Session) em.getDelegate();
		return session
				.createSQLQuery(
						"select distinct r.* from soahotel.reservation r "
								+ "natural join soahotel.account a "
								+ "natural join soahotel.room ro " + "where '"
								+ id + "' = r.res_acc_id")
				.addEntity(Reservation.class).list();
	}

	@Override
	public void addReservation(Reservation reservation) {
		em.persist(reservation);
		logger.info("Account saved successfully, AccountDetails = "
				+ reservation);
	}

	@Override
	public void removeReservation(Long id) {
		// Reservation reservation = em.getReference(Reservation.class,id);
		Reservation reservation = em.find(Reservation.class, id);
		em.remove(reservation);
		logger.info("Removed reservation with details: " + reservation);
	}

	@Override
	public List<Reservation> getHotelAndCustomerReservations(Long hotelId,
			Long accountId) {
		List<Reservation> hotelRes = getHotelReservations(hotelId);
		List<Reservation> accountRes = getReservations(accountId);
		List<Long> resIds = new ArrayList<Long>();
		for (Reservation res : accountRes) {
			if (res.getAccount().getAccountType().equals(AccountType.CUSTOMER)) {
				resIds.add(res.getId());
			}
		}

		Iterator<Reservation> resIterator = hotelRes.iterator();
		while (resIterator.hasNext()) {
			if (!resIds.contains(resIterator.next().getId())) {
				resIterator.remove();
			}
		}

		return hotelRes;
	}

	@Override
	public void updateReservation(Reservation reservation) {
		em.merge(reservation);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reservation> getReservations() {
		Session session = (Session) em.getDelegate();
		return session.createSQLQuery("select r.* from soahotel.reservation r")
				.addEntity(Reservation.class).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reservation> getReservations(Long hotelID, Long roomTypeID,
			Integer year) {
		Session session = (Session) em.getDelegate();
		return session
				.createSQLQuery(
						""
								+ "select distinct r.* from soahotel.reservation r "
								+ "inner join soahotel.room ro on ro.roo_id = r.res_roo_id "
								+ "where ro.roo_hot_id=" + hotelID
								+ " and ro.roo_rty_id=" + roomTypeID
								+ " and ( extract(year from r.res_start_date)="
								+ year
								+ " or extract(year from r.res_end_date)="
								+ year + ")").addEntity(Reservation.class)
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reservation> getHotelReservations(Long id) {
		Session session = (Session) em.getDelegate();
		return session
				.createSQLQuery(
						"select distinct r.* from soahotel.reservation r "
								+ "natural join soahotel.room ro where ro.roo_hot_id="
								+ id).addEntity(Reservation.class).list();
	}
}
