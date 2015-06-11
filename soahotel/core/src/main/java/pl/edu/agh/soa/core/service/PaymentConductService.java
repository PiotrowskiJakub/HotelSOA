package pl.edu.agh.soa.core.service;

import pl.edu.agh.soa.core.bean.Payment;

import javax.ejb.Remote;

/**
 * Created by Ala Czyz on 2015-06-10.
 */
@Remote
public interface PaymentConductService {
    public Payment payByCreditCard(Long paymentId, String creditCard);
    public Payment payByTransfer(Long paymentId, String bank);
}
