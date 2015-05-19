package pl.edu.agh.soa.core.service.registration.implementation;

import java.util.List;

import javax.ejb.Stateless;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import pl.edu.agh.soa.core.bean.Account;
import pl.edu.agh.soa.core.dao.AccountDAO;
import pl.edu.agh.soa.core.dao.implementation.AccountDAOImpl;
import pl.edu.agh.soa.core.service.registration.AccountService;

@Stateless
@Path("app")
public class AccountServiceImpl implements AccountService {

	private AccountDAO accountDAO = new AccountDAOImpl();
	
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

	@Override
	@GET
	public String test() {
		// TODO Auto-generated method stub
		return "dupa";
	}

}
