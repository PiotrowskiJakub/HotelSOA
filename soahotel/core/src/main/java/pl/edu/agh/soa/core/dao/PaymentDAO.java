package pl.edu.agh.soa.core.dao;

import java.util.List;

import javax.ejb.Local;

import pl.edu.agh.soa.core.bean.Payment;

/**
 * Created by Ala Czyz.
 */
@Local
public interface PaymentDAO {

    public List<Payment> listPayments();
    public List<Payment> listPaymentByUser(Long userId);
    public List<Payment> listPaymentByUserAndStatus(Long userId, Payment.Status status);
    public Payment getPayment(Long paymentId);
    public List<Payment> getPaymentByReservationId(Long reservationId);
    public void addPayment(Payment Payment);
    public void removePayment(Long paymentId);
    public void updatePayment(Payment Payment);
    public void removePaymentByResrvationId(Long id);
	public Payment listPaymentByReservation(Long id);
}
