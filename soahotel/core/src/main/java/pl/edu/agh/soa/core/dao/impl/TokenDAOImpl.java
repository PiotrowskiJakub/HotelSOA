package pl.edu.agh.soa.core.dao.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pl.edu.agh.soa.core.bean.Token;
import pl.edu.agh.soa.core.dao.TokenDAO;

@Local(value=TokenDAO.class)
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class TokenDAOImpl implements TokenDAO {
	
	@PersistenceContext(unitName="soahoteldb")
	EntityManager em;

	@Override
	public boolean saveToken(Token token) {
		em.persist(token);
		return true;
	}

	@Override
	public Token getToken(String mail) {
		
		return null;
	}
	
}
