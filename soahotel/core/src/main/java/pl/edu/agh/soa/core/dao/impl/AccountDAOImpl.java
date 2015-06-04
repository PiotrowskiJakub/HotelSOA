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

import org.apache.log4j.Logger;

import pl.edu.agh.soa.core.bean.Account;
import pl.edu.agh.soa.core.dao.AbstractDAO;
import pl.edu.agh.soa.core.dao.AccountDAO;

@Local(value=AccountDAO.class)
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AccountDAOImpl extends AbstractDAO implements AccountDAO  {
	
	@PersistenceContext(unitName="soahoteldb")
	EntityManager em;
	
	public AccountDAOImpl() {
		super();
		logger = Logger.getLogger(AccountDAOImpl.class);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void addAccount(Account account) {
		em.persist(account);
		logger.info("Account saved successfully, AccountDetails = " + account);
	}

	@Override
	public void updateAccount(Account account) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Account> listAccount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account getAccount(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeAccount(Integer id) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> getAllAccount() {
		logger.info("Listing all accounts");
		return em.createNativeQuery("select * from soahotel.account").getResultList();
	}

}
