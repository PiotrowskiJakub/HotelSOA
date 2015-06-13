package pl.edu.agh.soa.core.service.impl;

import pl.edu.agh.soa.core.bean.Payment;
import pl.edu.agh.soa.core.dao.PaymentDAO;
import pl.edu.agh.soa.core.service.PaymentManageService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by Ala Czyz.
 */

@Stateless
public class PaymentManageServiceImpl implements PaymentManageService {
    @EJB
    PaymentDAO  paymentDAO;

    @Override
    public List<Payment> getPaymentsList(Long userId) {
        return paymentDAO.listPaymentByUser(userId);
    }

    @Override
    public List<Payment> getUnpaidPaymentsList(Long userId) {
        return paymentDAO.listPaymentByUserAndStatus(userId, Payment.Status.UNPAID);
    }

    @Override
    public List<Payment> getOverduePaymentsList(Long userId) {
        return paymentDAO.listPaymentByUserAndStatus(userId, Payment.Status.OVERDUE);
    }

    @Override
    public Payment getPayment(Long userId, Long paymentId) {
        Payment payment = paymentDAO.getPayment(paymentId);
        if(payment.getUserId() != userId)
            return null;
        else return payment;
    }

    @Override
    public String getPaymentStatus(Long userId, Long paymentId) {
        Payment payment = paymentDAO.getPayment(paymentId);
        if(payment.getUserId() != userId)
            return null;
        else return payment.getStatus().name();
    }
}
