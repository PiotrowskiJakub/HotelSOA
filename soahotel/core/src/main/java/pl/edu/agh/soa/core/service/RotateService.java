package pl.edu.agh.soa.core.service;

/**
 * Created by Ala Czyz.
 */
public interface RotateService {
    //todo to be scheduled in quartz (java cron)
    public void createPaymentsFromReservations();
    public void changePaymentsStatusToOverDue();
}
