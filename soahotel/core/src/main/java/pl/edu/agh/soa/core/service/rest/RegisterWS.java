package pl.edu.agh.soa.core.service.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.cdi.CdiCamelContext;
import org.apache.camel.cdi.ContextName;

import pl.edu.agh.soa.core.bean.Account;
import pl.edu.agh.soa.core.dict.EmailMessages;
import pl.edu.agh.soa.core.service.RegistrationService;

@Stateless
@Path("registration")
public class RegisterWS {

	@EJB
	RegistrationService registrationService;

	@Inject
	@ContextName("camel-context")
	CdiCamelContext context;

	// @Override
	@POST
	@Path("account")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addAccount(Account account) {
		registrationService.addAccount(account);

		// Send email to new user
		ProducerTemplate producer = context.createProducerTemplate();
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("To", account.getContact().getMail());
		headers.put("Subject", EmailMessages.SUBJECT);
		producer.sendBodyAndHeaders("direct:mail", account.getFirstName()
				+ EmailMessages.MESSAGE, headers);

		return Response.ok().build();
	}

	// @Override
	public void updateAccount(Account account) {
		// TODO Auto-generated method stub

	}

	// @Override
	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAccount() {
		List<Account> accList = registrationService.getAccountList();
		return Response.ok(accList).build();
	}

	// @Override
	public Account getAccount(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	// @Override
	public void removeAccount(Integer id) {
		// TODO Auto-generated method stub

	}

	// @Override
	public String test() {
		// TODO Auto-generated method stub
		return null;
	}

}
