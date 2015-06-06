package pl.edu.agh.soa.core.dao.impl;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import pl.edu.agh.soa.core.dao.ReservationDAO;

/**
 * @author Piotr Konsek
 *
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ReservationDAOImp implements ReservationDAO {

}
