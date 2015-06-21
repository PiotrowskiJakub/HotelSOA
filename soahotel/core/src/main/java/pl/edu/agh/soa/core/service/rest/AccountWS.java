package pl.edu.agh.soa.core.service.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import pl.edu.agh.soa.core.bean.Account;
import pl.edu.agh.soa.core.interceptor.CheckToken;
import pl.edu.agh.soa.core.service.AccountService;

@Stateless
@Path("/accounts")
public class AccountWS {

	@EJB
	private AccountService accountService;
	
	@CheckToken
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Account> getAllAccount(@Context HttpServletRequest request) {
		return accountService.getAllAccount();
	}
}
