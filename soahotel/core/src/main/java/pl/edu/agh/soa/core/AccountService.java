package pl.edu.agh.soa.core;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface AccountService {
	public void addAccount(Account account);
	public void updateAccount(Account account);
	public List<Account> listAccount();
	public Account getAccount(Integer id);
	public void removeAccount(Integer id);
	public String test();
}
