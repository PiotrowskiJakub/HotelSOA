package pl.edu.agh.soa.core.service;

import pl.edu.agh.soa.core.bean.Payment;

/**
 * Created by Ala Czyz on 2015-06-10.
 */
public interface PaymentConductService {
    public Payment payByCreditCard(Long paymentId, String creditCard);
    public Payment payByTransfer(Long paymentId, String bank);
}
