package pl.edu.agh.soa.core;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired(required=true)
	private AccountDAO accountDAO;
	
	public void setAccountDAO(AccountDAO accountDAO){
		this.accountDAO = accountDAO;
	}
	
	@Override
	@Transactional
	public void addAccount(Account account) {
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

}
