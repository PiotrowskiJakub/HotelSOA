package pl.edu.agh.soa.core.dao;

import pl.edu.agh.soa.core.bean.Complaint;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by agnieszkaszczurek on 20.06.15.
 */

@Local
public interface ComplaintDAO {

    public void addComplaint(Complaint complaint);
    public Complaint getComplaintById(Long id);
    public void updateComplaint(Complaint complaint);
    public void removeComplaint(Long id);
    public List<Complaint> getAllComplaints();
    public Complaint getComplaintByReservaitonId(Long id);
}
