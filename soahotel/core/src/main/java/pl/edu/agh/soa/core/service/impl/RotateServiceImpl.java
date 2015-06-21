package pl.edu.agh.soa.core.service.impl;

import pl.edu.agh.soa.core.bean.AdditionalService;
import pl.edu.agh.soa.core.bean.Payment;
import pl.edu.agh.soa.core.bean.Reservation;
import pl.edu.agh.soa.core.dao.PaymentDAO;
import pl.edu.agh.soa.core.dao.ReservationDAO;
import pl.edu.agh.soa.core.service.RotateService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ala Czyz.
 */
@Stateless
public class RotateServiceImpl implements RotateService {

    private static final int TIME_TO_PAY = 7;
    private TimeUnit timeUnit;
    @EJB
    PaymentDAO paymentDAO;

    @EJB
    ReservationDAO reservationDAO;

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

        return new Payment(dueDate, calcualteCost(reservation), Payment.Status.UNPAID, reservation);

    }

    private BigDecimal calcualteCost(Reservation reservation) {
        long dateOffset = reservation.getEndDate().getTime() - reservation.getStartDate().getTime();
        timeUnit.toDays(dateOffset);

        BigDecimal cost = reservation.getRoom().getRoomType().getPrice().multiply(new BigDecimal(dateOffset));
        return cost.add(costOfAdditionalServices(reservation.getAdditionalServices()));

    }

    private BigDecimal costOfAdditionalServices(Set<AdditionalService> additionalServices) {
        BigDecimal cost = BigDecimal.ZERO;
        for (AdditionalService service : additionalServices) {
            cost.add(service.getServiceType().getPrice().multiply(new BigDecimal(service.getCount())));
        }
        return cost;
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
    public void changePaymentsStatusToOverDue() {
        List<Payment> payments = paymentDAO.listPayments();
        Date now = new Date();
        for (Payment payment : payments) {
            if (!payment.isPaid() && payment.getDueDate().before(now)) {
                payment.setStatus(Payment.Status.OVERDUE);
            }
        }

    }
}
