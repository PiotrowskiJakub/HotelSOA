package pl.edu.agh.soa.core.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import pl.edu.agh.soa.core.bean.Payment;
import pl.edu.agh.soa.core.dao.AbstractDAO;
import pl.edu.agh.soa.core.dao.PaymentDAO;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PaymentDAOImpl extends AbstractDAO implements PaymentDAO {

    @PersistenceContext(unitName = "soahoteldb")
    EntityManager entityManager;

    public PaymentDAOImpl() {
        super();
        logger = Logger.getLogger(AccountDAOImpl.class);
    }

    @Override
    public Payment getPayment(Long id) {
        return (Payment) entityManager.find(Payment.class, id);

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Payment> listPayments() {
        logger.info("Get all Payments");
        Session session = (Session) entityManager.getDelegate();
        return (List<Payment>) session.createSQLQuery("select p.*" +
                " from soahotel.payment p natural join soahotel.reservation r").addEntity(Payment.class).list();
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Payment> listPaymentByUser(Long userId) {
        Session session = (Session) entityManager.getDelegate();
        return (List<Payment>) session.createSQLQuery("select p.*" +
                " from soahotel.payment p natural join soahotel.reservation r where r.res_acc_id = " + userId).addEntity(Payment.class).list();
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Payment> getPaymentByReservationId(Long reservationId) {
        Session session = (Session) entityManager.getDelegate();
        return (List<Payment>) session.createSQLQuery("select p.*" +
                " from soahotel.payment p natural join soahotel.reservation r where r.res_id = " + reservationId).addEntity(Payment.class).list();
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Payment> listPaymentByUserAndStatus(Long userId, Payment.Status status) {
        Session session = (Session) entityManager.getDelegate();
        return (List<Payment>) session.createSQLQuery("select p.*" +
                " from soahotel.payment p natural join soahotel.reservation r where r.res_acc_id = " + userId +
                "and p.pay_status = " + status.ordinal()).addEntity(Payment.class).list();
    }

    @Override
    public void addPayment(Payment payment) {
        entityManager.persist(payment);
        logger.info("Account saved successfully, AccountDetails = " + payment);
    }

    @Override
    public void removePayment(Long id) {
        Payment payment = entityManager.find(Payment.class, id);
        entityManager.remove(payment);
        logger.info("Removed payment with details: " + payment);
    }

    @Override
    public void updatePayment(Payment payment) {
        entityManager.merge(payment);
    }
    
	@Override
	public void removePaymentByResrvationId(Long id) {
		 Session session = (Session) entityManager.getDelegate();
		 @SuppressWarnings("unchecked")
		List<Payment> payments =  session.createSQLQuery("select p.* from soahotel.payment p where p.res_id=" + id).addEntity(Payment.class).list();
		 if(payments != null && !payments.isEmpty())
			 entityManager.remove(payments.get(0));
	}

	@Override
	public Payment listPaymentByReservation(Long reservationId) {
		Session session = (Session) entityManager.getDelegate();
        return (Payment) session.createSQLQuery("select p.*" +
                " from soahotel.payment p natural join soahotel.reservation r where r.res_id = " + reservationId).addEntity(Payment.class).uniqueResult();
	}
}
