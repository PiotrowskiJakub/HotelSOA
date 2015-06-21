package pl.edu.agh.soa.core.service;

import java.util.List;

import javax.ejb.Remote;

import pl.edu.agh.soa.core.bean.Account;

@Remote
public interface AccountService {

	public List<Account> getAllAccount();
}
