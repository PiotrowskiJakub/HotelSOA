package pl.edu.agh.soa.core.service.test;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pl.edu.agh.soa.core.bean.Account;
import pl.edu.agh.soa.core.bean.Address;
import pl.edu.agh.soa.core.bean.Contact;
import pl.edu.agh.soa.core.dict.AccountStatus;
import pl.edu.agh.soa.core.dict.AccountType;
import pl.edu.agh.soa.core.service.RegistrationService;


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
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String getUser(){
		account = new Account();
		account.setPassword("pass");
		account.setBirthDate(new Date());
		account.setAccountStatus(AccountStatus.INACTIVE);
		account.setAccountType(AccountType.EMPLOYEE);
		account.setFirstName("Piotr");
		account.setLastName("Konsek");
		Address address = new Address();
		address.setCity("City");
		address.setCountry("Country");
		address.setFlatNumber(11);
		address.setState("State");
		address.setStreet("Street");
		address.setPostalCode("44200");
		address.setHouseNumber("5");
		
		Contact contact = new Contact();
		contact.setMail("mail@gmail.com");
		contact.setPhone("663355925");
		
		account.setAddress(address);
		account.setContact(contact);
		accountService.addAccount(account);
		
		return "This is rest test ;)";
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
