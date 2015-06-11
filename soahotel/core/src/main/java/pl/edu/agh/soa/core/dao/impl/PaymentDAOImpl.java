package pl.edu.agh.soa.core.dao.impl;

import org.apache.log4j.Logger;
import pl.edu.agh.soa.core.bean.Payment;
import pl.edu.agh.soa.core.dao.AbstractDAO;
import pl.edu.agh.soa.core.dao.PaymentDAO;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    @SuppressWarnings("unchecked")
    @Override
    public List<Payment> listPayment() {
        logger.info("Get all Payments");
        return entityManager.createNativeQuery("select * from soahotel.payment").getResultList();
    }

    @Override
    public List<Payment> listPaymentByUser(Long userId) {
        return (List<Payment>) entityManager.createNativeQuery("select pay_id,pay_due_date, pay_gross_cost, pay_status, pay_res_id" +
                " from soahotel.payment natural join soahotel.reservation " +
                "where '" + userId + "' = res_acc_id").getResultList();

    }

    @Override
    public List<Payment> listPaymentByUserAndStatus(Long userId, Payment.Status status) {
        return (List<Payment>) entityManager.createNativeQuery("select * from soahotel.payment natural join soahotel.reservation "
                        + " where '" + userId + "' = soahotel.reservation.res_acc_id"
        ).getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Payment getPayment(Long paymentId) {
        List<Payment> results =  (List<Payment>) entityManager.createNativeQuery("select * from soahotel.payment where '" + paymentId + "' = soahotel.payment.pay_id").getResultList();
        return  results.get(0);
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
