package pl.edu.agh.soa.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestRest {
	
	Account account;
	
	@Autowired(required=true)
	private AccountService accountService;
	
//	@Autowired
//	private AccountDAO AccountDAO;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody String getUser(){
		account = new Account();
		account.setAccountStatus(AccountStatus.INACTIVE);
		account.setAccountType(AccountType.EMPLOYEE);
		account.setFirstName("Piotr");
		account.setLastName("Konsek");
		
//		AccountService accountService = new AccountService();
//		accountService.setAccountDAO(AccountDAO);
		accountService.addAccount(account);
		
//		user.setAge(22);
		return "This is rest test ;)";
//		return user;
	}
	
	
}
