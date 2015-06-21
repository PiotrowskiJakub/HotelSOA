package pl.edu.agh.soa.core.service.impl;

import pl.edu.agh.soa.core.bean.AdditionalService;
import pl.edu.agh.soa.core.bean.Reservation;
import pl.edu.agh.soa.core.dao.ReservationDAO;
import pl.edu.agh.soa.core.service.CheckCostService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.math.BigDecimal;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ala Czyz.
 */
@Stateless
public class CheckCostServiceImpl implements CheckCostService {
    @EJB
    ReservationDAO reservationDAO;

    @Override
    public BigDecimal getCurrentCost(Long reservationId) {
        Reservation reservation = reservationDAO.getReservation(reservationId);
        long dateOffset = reservation.getEndDate().getTime() - reservation.getStartDate().getTime();
        TimeUnit.MILLISECONDS.toDays(dateOffset);

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
}
