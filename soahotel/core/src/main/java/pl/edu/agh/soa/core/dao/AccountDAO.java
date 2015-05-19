package pl.edu.agh.soa.core.dao;

import java.util.List;

import pl.edu.agh.soa.core.bean.Account;

public interface AccountDAO {
	public void addAccount(Account account);
	public void updateAccount(Account account);
	public List<Account> listAccount();
	public Account getAccount(Integer id);
	public void removeAccount(Integer id);
}
