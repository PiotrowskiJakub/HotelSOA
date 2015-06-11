package pl.edu.agh.soa.core.service.impl;

import pl.edu.agh.soa.core.service.CheckCostService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.math.BigDecimal;

/**
 * Created by Ala Czyz.
 */
@Stateless
public class CheckCostServiceImpl implements CheckCostService {

    // todo connect with dao
//    room_type.price * ( długość pobytu w dniach ) + sum( additional_service_type.price );

    @Override
    public BigDecimal getCurrentCost(Long reservationId) {
        return new BigDecimal("123,45");
    }
}
