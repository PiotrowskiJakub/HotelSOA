package pl.edu.agh.soa.core.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;

import pl.edu.agh.soa.core.bean.Account;
import pl.edu.agh.soa.core.dao.AccountDAO;
import pl.edu.agh.soa.core.dict.AccountStatus;
import pl.edu.agh.soa.core.service.RegistrationService;

@Stateless
public class RegistrationServiceImpl implements RegistrationService {

	@EJB
	private AccountDAO accountDAO;
	
	public void setAccountDAO(AccountDAO accountDAO){
		this.accountDAO = accountDAO;
	}
	
	@Override
	public void addAccount(Account account) {
//		TODO
		//every new account is inactive, until mail confirmation
//		account.setAccountStatus(AccountStatus.INACTIVE);
		account.setAccountStatus(AccountStatus.ACTIVE);
		this.accountDAO.addAccount(account);
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

	@Override
	@GET
	public String test() {
		// TODO Auto-generated method stub
		return "dupa";
	}

}
