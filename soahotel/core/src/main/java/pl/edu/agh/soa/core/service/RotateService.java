package pl.edu.agh.soa.core.service;

import javax.ejb.Remote;

/**
 * Created by Ala Czyz.
 */

@Remote
public interface RotateService {
    void createPaymentsFromReservations();
    void changePaymentsStatusToOverdue();
}
