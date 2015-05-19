package pl.edu.agh.soa.core.service.registration.api;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pl.edu.agh.soa.core.bean.Account;
import pl.edu.agh.soa.core.service.registration.RegistrationService;

@Stateless
@Path("registration")
public class RegisterWS{
	
	@EJB
	RegistrationService registrationService;

//	@Override
	@PUT
	@Path("account")
	@Consumes(MediaType.APPLICATION_JSON)
	public void addAccount(Account account) {
		registrationService.addAccount(account);
	}

//	@Override
	public void updateAccount(Account account) {
		// TODO Auto-generated method stub
		
	}

//	@Override
	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Account> listAccount() {
		List<Account> accList = new ArrayList<Account>();
		accList.add(new Account());
		accList.get(0).setFirstName("Piotr");
		return accList;
	}

//	@Override
	public Account getAccount(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public void removeAccount(Integer id) {
		// TODO Auto-generated method stub
		
	}

//	@Override
	public String test() {
		// TODO Auto-generated method stub
		return null;
	}

}
