package pl.edu.agh.soa.core.dao.impl;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;

import pl.edu.agh.soa.core.bean.Complaint;
import pl.edu.agh.soa.core.dao.ComplaintDAO;

/**
 * Created by agnieszkaszczurek on 20.06.15.
 */

@Local(value = ComplaintDAO.class)
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)

public class ComplaintDAOImpl implements ComplaintDAO {

    @PersistenceContext(unitName="soahoteldb")
    EntityManager em;


    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void addComplaint(Complaint complaint) {
        em.merge(complaint);
    }

    @Override
    public Complaint getComplaintById(Long id) {

        return em.find(Complaint.class, id);
    }

    @Override
    public void updateComplaint(Complaint complaint) {
        em.merge(complaint);
    }

    @Override
    public void removeComplaint(Long id) {
        em.remove(em.find(Complaint.class, id));
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Complaint> getAllComplaints() {
        Session session = (Session) em.getDelegate();
        return (List<Complaint>) session.createSQLQuery("select c.* from soahotel.complaint c").addEntity(Complaint.class).list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Complaint getComplaintByReservaitonId(Long id) {
        Session session = (Session) em.getDelegate();  
		List<Complaint> complaints = session.createSQLQuery("select c.* from soahotel.complaint c where c.com_res_id =" +id).addEntity(Complaint.class).list();
        if(complaints != null && !complaints.isEmpty())
        	return  complaints.get(0);
        else
        	return null;
    }

	@SuppressWarnings("unchecked")
	@Override
	public void deleteComplaintByReservationId(Long id) {
		Session session = (Session) em.getDelegate();
		List<Complaint> complaints = session.createSQLQuery("select c.* from soahotel.complaint c where c.com_res_id =" +id).addEntity(Complaint.class).list();
        if(complaints != null && !complaints.isEmpty())
        	for(Complaint complaint : complaints)
        		em.remove(complaint);
	}
}
