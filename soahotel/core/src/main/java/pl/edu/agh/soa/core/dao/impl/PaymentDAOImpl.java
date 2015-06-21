package pl.edu.agh.soa.core.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import pl.edu.agh.soa.core.bean.Payment;
import pl.edu.agh.soa.core.dao.AbstractDAO;
import pl.edu.agh.soa.core.dao.PaymentDAO;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

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
    public Payment getPayment(Long paymentId) {
        Payment payment = entityManager.find(Payment.class, paymentId);
        return payment;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Payment> listPayments() {
        logger.info("Get all Payments");
        Session session = (Session) entityManager.getDelegate();
        return (List<Payment>)  entityManager.createNativeQuery("select * from soahotel.payment").getResultList();
//        return (List<Payment>) session.createSQLQuery("select p.*" +
//                " from soahotel.payment p").addEntity(Payment.class).list();
    }

    @Override
    public List<Payment> listPaymentByUser(Long userId) {
        Session session = (Session) entityManager.getDelegate();
        return (List<Payment>) entityManager.createNativeQuery("select pay_id,res_id, pay_due_date, pay_gross_cost, pay_status, res_paid" +
                " from soahotel.payment natural join soahotel.reservation " +
                "where  res_acc_id = " + userId).getResultList();

    }

    @Override
    public List<Payment> listPaymentByUserAndStatus(Long userId, Payment.Status status) {
        return (List<Payment>) entityManager.createNativeQuery("select * from soahotel.payment natural join soahotel.reservation "
                        + " where '" + userId + "' = soahotel.reservation.res_acc_id"
        ).getResultList();
    }


    @SuppressWarnings("unchecked")
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
}
