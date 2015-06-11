package pl.edu.agh.soa.core.service.impl;

import pl.edu.agh.soa.core.bean.Payment;
import pl.edu.agh.soa.core.service.PaymentConductService;

import javax.ejb.Stateless;

/**
 * Created by Ala Czyz.
 */
@Stateless
public class PaymentConductServiceImpl implements PaymentConductService{


    @Override
    public Payment payByCreditCard(Long paymentId, String creditCard) {
        return null;
    }

    @Override
    public Payment payByTransfer(Long paymentId, String bank) {
        return null;
    }
}
