package pl.edu.agh.soa.core.service.rest;

import pl.edu.agh.soa.core.bean.Payment;
import pl.edu.agh.soa.core.service.PaymentConductService;
import pl.edu.agh.soa.core.service.PaymentManageService;
import pl.edu.agh.soa.core.service.RotateService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Ala Czyz.
 */

@Stateless
@Path("/payment")
public class PaymentManageWS {

    @EJB
    PaymentManageService paymentManagingService;


    @EJB
    PaymentConductService paymentConductService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPayments() {
        List<Payment> paymentList = paymentManagingService.getPaymentsList();
        return Response.ok(paymentList, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Path("{user_id}/{payment_id}/pay")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pay(@PathParam("user_id") Long userId,
                        @PathParam("payment_id") Long paymentId,
                        @QueryParam("transfer") String bankName,
                        @QueryParam("credit_card") String creditCard) {

        //only one is needed
        if ((creditCard != null && bankName != null) || (creditCard == null && bankName == null)) {
           return Response.status(Response.Status.BAD_REQUEST).entity("One and only one is requried: bankName or creditCard").type("text/plain").build();
        }
        Payment payment = null;
        if (creditCard != null) {
            payment = paymentConductService.payByCreditCard(paymentId, creditCard);
        } else if (bankName != null) {
            payment = paymentConductService.payByTransfer(paymentId, bankName);
        }
        if (payment.isPaid()) {
            return Response.ok(payment, MediaType.APPLICATION_JSON).build();
        } else return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }


    @GET
    @Path("/{user_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPaymentsList(@PathParam("user_id") Long userId) {
        List<Payment> paymentList = paymentManagingService.getPaymentsList(userId);
        return Response.ok(paymentList, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/{user_id}/unpaid")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUnpaidPaymentsList(@PathParam("user_id") Long userId) {
        List<Payment> paymentList = paymentManagingService.getUnpaidPaymentsList(userId);
        return Response.ok(paymentList, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/{user_id}/overdue")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOverduePaymentsList(@PathParam("user_id") Long userId) {
        List<Payment> paymentList = paymentManagingService.getOverduePaymentsList(userId);
        return Response.ok(paymentList, MediaType.APPLICATION_JSON).build();
    }


    @GET
    @Path("/{user_id}/{payment_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPayment(@PathParam("user_id") Long userId, @PathParam("payment_id") Long paymentId) {
        Payment payment = paymentManagingService.getPayment(userId, paymentId);
        return Response.ok(payment, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/{user_id}/{payment_id}/status")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPaymentStatus(@PathParam("user_id") Long userId, @PathParam("payment_id") Long paymentId) {
        String paymentStatus = paymentManagingService.getPaymentStatus(userId, paymentId);
        return Response.ok(paymentStatus, MediaType.APPLICATION_JSON).build();
    }

    //TODO remove in final version - for test only
    @EJB
    RotateService rotateService;

    @PUT
    @Path("createPayments")
    public Response createPayments() {
        rotateService.createPaymentsFromReservations();
        rotateService.changePaymentsStatusToOverdue();
        return Response.ok().build();
    }

}
