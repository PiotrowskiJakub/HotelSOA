package pl.edu.agh.soa.core.service.test;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pl.edu.agh.soa.core.bean.Account;
import pl.edu.agh.soa.core.dict.AccountStatus;
import pl.edu.agh.soa.core.dict.AccountType;
import pl.edu.agh.soa.core.service.registration.RegistrationService;


@Stateless
@Path("/rest")
public class TestRest {
	
	Account account;
	
	public TestRest(){
		
	}

//	@Autowired(required=true)
	@EJB
	private RegistrationService accountService;
	
//	@Autowired
//	private AccountDAO AccountDAO;
	
	@GET
//	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String getUser(){
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
	
//	@RequestMapping(method = RequestMethod.PUT )
//	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@PUT
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public void putAccount(Account account){
		System.out.println("It works!");
		accountService.addAccount(account);
	}
	
	
}
