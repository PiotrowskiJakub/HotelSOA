package pl.edu.agh.soa.core.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

public class AbstractDAO {
	
	protected EntityManagerFactory entityManagerFactory;
	
	protected EntityManager entityManager;
	
	protected static Logger logger;

	public AbstractDAO(){
		entityManagerFactory = Persistence.createEntityManagerFactory("soahoteldb");
		entityManager = entityManagerFactory.createEntityManager();
	}
}
