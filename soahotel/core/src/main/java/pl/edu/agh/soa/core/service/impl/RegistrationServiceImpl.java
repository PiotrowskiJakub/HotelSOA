package pl.edu.agh.soa.core.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pl.edu.agh.soa.core.bean.Account;
import pl.edu.agh.soa.core.dao.AccountDAO;
import pl.edu.agh.soa.core.dao.TokenDAO;
import pl.edu.agh.soa.core.dict.AccountStatus;
import pl.edu.agh.soa.core.service.RegistrationService;

@Stateless
public class RegistrationServiceImpl implements RegistrationService {

	@EJB
	private AccountDAO accountDAO;
	
	@EJB
	private TokenDAO tokenDAO;
	
	public void setAccountDAO(AccountDAO accountDAO){
		this.accountDAO = accountDAO;
	}
	
	@Override
	public Account addAccount(Account account) {
		account.setAccountStatus(AccountStatus.INACTIVE);
		return accountDAO.addAccount(account);
	}

	@Override
	public void updateAccount(Account account) {
		accountDAO.updateAccount(account);
	}

	@Override
	public List<Account> listAccount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account getAccount(Long id) {
		return accountDAO.getAccount(id);
	}
	
	@Override
	public Account getAccount(String mail) {
		return accountDAO.getAccount(mail);
	}

	@Override
	public void removeAccount(Integer id) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public List<Account> getAccountList() {
		return this.accountDAO.getAllAccount();
	}

	@Override
	public String test() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkToken(String token) {
		return tokenDAO.checkToken(token);
	}
}
