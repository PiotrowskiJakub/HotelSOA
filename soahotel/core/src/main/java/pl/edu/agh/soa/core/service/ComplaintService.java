package pl.edu.agh.soa.core.service;

import pl.edu.agh.soa.core.bean.Complaint;
import pl.edu.agh.soa.core.dto.Mail;

import javax.ejb.Remote;

import java.util.List;

/**
 * Created by agnieszkaszczurek on 20.06.15.
 */
@Remote
public interface ComplaintService {

    public void createComplaint(Complaint complaint);
    public Complaint getComplaint(Long id);
    public void updateComplaint(Complaint complaint);
    public void deleteComplaint(Long id);
    public List<Complaint> getAllComplaints();
    public Complaint getComplaintByReservationId(Long id);
	public void deleteComplaintByReservationId(Long id);
	public void sendMailToUser(Mail mail);
}


