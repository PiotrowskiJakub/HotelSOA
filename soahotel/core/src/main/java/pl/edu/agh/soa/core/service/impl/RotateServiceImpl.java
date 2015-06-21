package pl.edu.agh.soa.core.service.impl;

import pl.edu.agh.soa.core.bean.Payment;
import pl.edu.agh.soa.core.bean.Reservation;
import pl.edu.agh.soa.core.dao.PaymentDAO;
import pl.edu.agh.soa.core.dao.ReservationDAO;
import pl.edu.agh.soa.core.service.CheckCostService;
import pl.edu.agh.soa.core.service.RotateService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Ala Czyz.
 */
@Stateless
public class RotateServiceImpl implements RotateService {

    private static final int TIME_TO_PAY = 7;
    @EJB
    PaymentDAO paymentDAO;

    @EJB
    ReservationDAO reservationDAO;

    @EJB
    CheckCostService checkCostService;

    @Override
    public void createPaymentsFromReservations() {
        List<Reservation> allReservations = reservationDAO.getReservations();
        List<Payment> payments = createPaymentsFromReservations(allReservations);
        addPayments(payments);
    }

    private void addPayments(List<Payment> payments) {
        for (Payment payment : payments) {
            paymentDAO.addPayment(payment);
        }
    }


    private Payment createPayment(Reservation reservation) {
        Calendar c = Calendar.getInstance();
        c.setTime(reservation.getEndDate());
        c.add(Calendar.DATE, TIME_TO_PAY);
        Date dueDate = c.getTime();

        Payment payment = new Payment();
        payment.setDueDate(dueDate);
        payment.setStatus(Payment.Status.UNPAID);
        payment.setGrossCost(calcualteCost(reservation));
        payment.setReservation(reservation);
        return payment;

    }

    private BigDecimal calcualteCost(Reservation reservation) {
        return checkCostService.getCurrentCost(reservation.getId());
    }

    private List<Payment> createPaymentsFromReservations(List<Reservation> allReservations) {
        List<Payment> payments = new ArrayList<>();
        Date now = new Date();
        for (Reservation reservation : allReservations) {
            if (reservation.getEndDate().before(now)) {
                payments.add(createPayment(reservation));
            }
        }
        return payments;
    }

    @Override
    public void changePaymentsStatusToOverdue() {
        List<Payment> payments = paymentDAO.listPayments();
        Date now = new Date();
        for (Payment payment : payments) {
            if (!payment.isPaid() && payment.getDueDate().before(now)) {
                payment.setStatus(Payment.Status.OVERDUE);
            }
        }

    }
}
