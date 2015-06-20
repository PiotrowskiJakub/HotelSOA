package pl.edu.agh.soa.core.dao.impl;

import org.hibernate.Session;
import pl.edu.agh.soa.core.bean.Complaint;
import pl.edu.agh.soa.core.dao.ComplaintDAO;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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
    public Complaint getComplaintByReservaitonId(Long id) {
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
}
