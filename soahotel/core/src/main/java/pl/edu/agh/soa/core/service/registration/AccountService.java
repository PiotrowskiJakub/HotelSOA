package pl.edu.agh.soa.core.service.registration;

import java.util.List;

import javax.ejb.Remote;

import pl.edu.agh.soa.core.bean.Account;

@Remote
public interface AccountService {
	public void addAccount(Account account);
	public void updateAccount(Account account);
	public List<Account> listAccount();
	public Account getAccount(Integer id);
	public void removeAccount(Integer id);
	public String test();
}
