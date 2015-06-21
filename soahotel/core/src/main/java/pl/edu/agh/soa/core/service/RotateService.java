package pl.edu.agh.soa.core.service;

import javax.ejb.Remote;

/**
 * Created by Ala Czyz.
 */

@Remote
public interface RotateService {
    //todo to be scheduled in quartz (java cron)
    public void createPaymentsFromReservations();
    public void changePaymentsStatusToOverDue();
}
