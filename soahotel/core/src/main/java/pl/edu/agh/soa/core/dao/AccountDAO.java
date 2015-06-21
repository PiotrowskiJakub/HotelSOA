package pl.edu.agh.soa.core.dao;

import java.util.List;

import javax.ejb.Local;

import pl.edu.agh.soa.core.bean.Account;

@Local
public interface AccountDAO {
	public Account addAccount(Account account);
	public void updateAccount(Account account);
	public List<Account> listAccount();
	public Account getAccount(Long id);
	public void removeAccount(Integer id);
	public List<Account> getAllAccount();
	public Account getAccount(String mail);
}
