package pl.edu.agh.soa.core.service;

import javax.ejb.Remote;
import java.math.BigDecimal;

/**
 * Created by Ala Czyz.
 */
@Remote
public interface CheckCostService {
    BigDecimal getCurrentCost(Long id);
}
