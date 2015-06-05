package pl.edu.agh.soa.core.service.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pl.edu.agh.soa.core.bean.Account;
import pl.edu.agh.soa.core.service.RegistrationService;

@Stateless
@Path("registration")
public class RegisterWS{
	
	@EJB
	RegistrationService registrationService;

//	@Override
	@POST
	@Path("account")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addAccount(Account account) {
		registrationService.addAccount(account);
		return Response.ok().build();
	}

//	@Override
	public void updateAccount(Account account) {
		// TODO Auto-generated method stub
		
	}

//	@Override
	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAccount() {
		List<Account> accList = registrationService.getAccountList();
		return Response.ok(accList).build();
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
