package pl.edu.agh.soa.core;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class AccountDAOImpl implements AccountDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	private static final Logger logger = Logger.getLogger(AccountDAOImpl.class);

	@Override
	public void addAccount(Account account) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(account);
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
