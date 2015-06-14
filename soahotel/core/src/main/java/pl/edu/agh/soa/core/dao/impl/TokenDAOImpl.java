package pl.edu.agh.soa.core.dao.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;

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

	@Override
	public boolean checkToken(String token) {
		boolean checkTokenFlag = false;
		Session session = (Session) em.getDelegate();
		Token tokenObj = (Token) session.createSQLQuery("select * from soahotel.token where tok_token = '" + token + "'").addEntity(Token.class).uniqueResult();
		if(tokenObj != null) {
			em.remove(tokenObj);
			checkTokenFlag = true;
		}
		return checkTokenFlag;
	}
	
}
