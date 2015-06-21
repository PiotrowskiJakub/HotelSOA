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

import pl.edu.agh.soa.core.bean.Invoice;
import pl.edu.agh.soa.core.dao.InvoiceDAO;

@Local(value=InvoiceDAO.class)
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class InvoiceDAOImpl implements InvoiceDAO {

	@PersistenceContext(unitName="soahoteldb")
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<Invoice> getInvoices() {
		Session session = (Session) em.getDelegate();
		return (List<Invoice>) session.createSQLQuery("select i.* from soahotel.invoice i").addEntity(Invoice.class).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<Invoice> getUserInvoices(Long userId) {
		Session session = (Session) em.getDelegate();
		return (List<Invoice>) session.createSQLQuery("select i.* from soahotel.invoice i where i.inv_acc_id =" + userId).addEntity(Invoice.class).list();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public byte[] getInvoiceFile(Long invoiceId) {
		Session session = (Session) em.getDelegate();
		return (byte[]) session.createSQLQuery("select i.inv_file from soahotel.invoice i where i.inv_id =" + invoiceId).uniqueResult();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void addInvoice(Invoice invoice) {
		em.persist(invoice);
	}
}
