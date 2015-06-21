package pl.edu.agh.soa.core.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pl.edu.agh.soa.core.bean.Account;
import pl.edu.agh.soa.core.dao.AccountDAO;
import pl.edu.agh.soa.core.service.AccountService;

@Stateless
public class AccountServiceImpl implements AccountService {

	@EJB
	private AccountDAO accountDAO;
	
	@Override
	public List<Account> getAllAccount() {
		return accountDAO.getAllAccount();
	}

}
