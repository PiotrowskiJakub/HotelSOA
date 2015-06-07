package pl.edu.agh.soa.core.dao.impl;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import pl.edu.agh.soa.core.bean.Reservation;
import pl.edu.agh.soa.core.dao.AbstractDAO;
import pl.edu.agh.soa.core.dao.ReservationDAO;

import java.util.List;

/**
 * @author Agnieszka Szczurek
 *
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ReservationDAOImpl extends AbstractDAO implements ReservationDAO {

    @PersistenceContext(unitName="soahoteldb")
    EntityManager em;

    public ReservationDAOImpl() {
        super();
        logger = Logger.getLogger(AccountDAOImpl.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Reservation> listReservation() {
        logger.info("Get all reservations");
        return em.createNativeQuery("select * from soahotel.reservation").getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Reservation getReservation(Long id) {
        return (Reservation) em.createNativeQuery("select * from soahotel.reservation where '"+id+"' = soahotel.reservation.res_id");
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Reservation> getReservations(Long id) {
        return em.createNativeQuery("select * from soahotel.reservation where '" +id+ "' = soahotel.reservation.res_acc_id").getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addReservation(Reservation reservation) {
        em.persist(reservation);
        logger.info("Account saved successfully, AccountDetails = " + reservation);
    }

    @Override
    public void removeReservation(Long id) {
        //Reservation reservation = em.getReference(Reservation.class,id);
        Reservation reservation = em.find(Reservation.class, id);
        em.remove(reservation);
        logger.info("Removed reservation with details: " + reservation);
    }

    @Override
    public void updateReservation(Reservation reservation) {
        em.merge(reservation);
    }
}
