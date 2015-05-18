package pl.edu.agh.soa.core;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.apache.log4j.Logger;

public class AccountDAOImpl implements AccountDAO {
	
//	private SessionFactory sessionFactory;
	
//	@PersistenceContext(name="soahoteldb")
//	private EntityManager entityManager;
	
	//@PersistenceUnit(unitName = "soahoteldb")
	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soahoteldb");
	
//	public void setSessionFactory(SessionFactory sessionFactory){
//		this.sessionFactory = sessionFactory;
//	}
	
	private static final Logger logger = Logger.getLogger(AccountDAOImpl.class);

	@Override
	public void addAccount(Account account) {
//		Session session = this.sessionFactory.getCurrentSession();	
//		session.persist(account);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.persist(account);
		logger.info("Account saved successfully, AccountDetails = " + account);
		entityManager.close();
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
