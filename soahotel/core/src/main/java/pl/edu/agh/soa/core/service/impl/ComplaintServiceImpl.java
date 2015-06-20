package pl.edu.agh.soa.core.service.impl;

import pl.edu.agh.soa.core.bean.Complaint;
import pl.edu.agh.soa.core.dao.ComplaintDAO;
import pl.edu.agh.soa.core.service.ComplaintService;

import javax.ejb.EJB;
import java.util.List;

/**
 * Created by agnieszkaszczurek on 20.06.15.
 */
public class ComplaintServiceImpl  implements ComplaintService{


    @EJB
    ComplaintDAO complaintDAO;


    @Override
    public void createComplaint(Complaint complaint) {
        complaintDAO.addComplaint(complaint);
    }

    @Override
    public Complaint getComplaint(Long id) {
        return complaintDAO.getComplaintByReservaitonId(id);
    }

    @Override
    public void updateComplaint(Complaint complaint) {
        complaintDAO.updateComplaint(complaint);
    }

    @Override
    public void deleteComplaint(Long id) {
        complaintDAO.removeComplaint(id);
    }

    @Override
    public List<Complaint> getAllComplaints() {
        return complaintDAO.getAllComplaints();
    }
}
