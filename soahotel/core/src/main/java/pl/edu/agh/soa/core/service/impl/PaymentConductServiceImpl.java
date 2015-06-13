package pl.edu.agh.soa.core.service.impl;

import pl.edu.agh.soa.core.bean.Payment;
import pl.edu.agh.soa.core.dao.PaymentDAO;
import pl.edu.agh.soa.core.service.PaymentConductService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

/**
 * Created by Ala Czyz.
 */
@Stateless
public class PaymentConductServiceImpl implements PaymentConductService {

    @EJB
    PaymentDAO paymentDAO;

//    @EJB
    //some mockedExternalService

    @Override
    public Payment payByCreditCard(Long paymentId, String creditCard) {
        Payment payment = paymentDAO.getPayment(paymentId);
        // todo
//        Response response = mockedExternalService.chargeCreditCard(creditCard,payment.getGrossCost());
//        if(Response.Status.OK.equals(response.getStatus())){
//            payment.setStatus(Payment.Status.PAID);
//        }
        return payment;
    }

    @Override
    public Payment payByTransfer(Long paymentId, String bank) {
        Payment payment = paymentDAO.getPayment(paymentId);
        // todo
//        Response response = mockedExternalService.redirectToBankSite(bank,payment.getGrossCost());
//        if(Response.Status.OK.equals(response.getStatus())){
//            payment.setStatus(Payment.Status.PAID);
//        }
        return payment;
    }
}
