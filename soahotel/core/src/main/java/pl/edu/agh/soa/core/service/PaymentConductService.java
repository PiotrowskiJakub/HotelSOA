package pl.edu.agh.soa.core.service;

import pl.edu.agh.soa.core.bean.Payment;

import javax.ejb.Remote;

/**
 * Created by Ala Czyz.
 */
@Remote
public interface PaymentConductService {
    Payment payByCreditCard(Long paymentId, String creditCard);
    Payment payByTransfer(Long paymentId, String bank);
}
