package pl.edu.agh.soa.core.dao.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pl.edu.agh.soa.core.bean.AdditionalServiceType;
import pl.edu.agh.soa.core.dao.AstDao;

@Local(value=AstDao.class)
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AstDaoImpl implements AstDao {

	@PersistenceContext(unitName="soahoteldb")
	EntityManager em;
	
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void add(AdditionalServiceType ast) {
		em.persist(ast);
	}

}
