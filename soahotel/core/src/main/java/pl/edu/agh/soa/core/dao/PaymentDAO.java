package pl.edu.agh.soa.core.dao;

import pl.edu.agh.soa.core.bean.Payment;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by Ala Czyz.
 */
@Local
public interface PaymentDAO {

    public List<Payment> listPayment();
    public List<Payment> listPaymentByUser(Long userId);
    public List<Payment> listPaymentByUserAndStatus(Long userId, Payment.Status status);
    public Payment getPayment(Long paymentId);
    public void addPayment(Payment Payment);
    public void removePayment(Long paymentId);
    public void updatePayment(Payment Payment);
    
}
