package pl.edu.agh.soa.core.dao.implementation;

import java.util.List;

import org.apache.log4j.Logger;

import pl.edu.agh.soa.core.bean.Account;
import pl.edu.agh.soa.core.dao.AbstractDAO;
import pl.edu.agh.soa.core.dao.AccountDAO;

public class AccountDAOImpl extends AbstractDAO implements AccountDAO  {
	
	public AccountDAOImpl() {
		super();
		logger = Logger.getLogger(AccountDAOImpl.class);
	}

	@Override
	public void addAccount(Account account) {
		
		entityManager.getTransaction().begin();
		entityManager.persist(account);
		entityManager.getTransaction().commit();
		entityManager.close();
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

}
