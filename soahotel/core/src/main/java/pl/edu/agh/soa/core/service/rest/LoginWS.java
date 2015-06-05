package pl.edu.agh.soa.core.service.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pl.edu.agh.soa.core.bean.Account;
import pl.edu.agh.soa.core.dao.AccountDAO;

@Stateless
@Path("/login")
public class LoginWS {
	
	@EJB
	AccountDAO accountDao;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/in/{mail}/{password}")
	public Response login(@PathParam("mail") String mail, @PathParam("password") String password, @Context HttpServletRequest request){
		HttpSession session = request.getSession();
		Account account = accountDao.getAccount(mail);
		if(account == null)
			return Response.status(Response.Status.NOT_FOUND.getStatusCode()).entity("There is no user with this login!").build();
		if(!account.getPassword().equals(password))
			return Response.status(Response.Status.UNAUTHORIZED.getStatusCode()).entity("Wrong password").build();
		session.setAttribute("LOGIN", "OK");
		return Response.ok().build();
	}
	
	@POST
	@Path("/out")
	public Response logout(){
		return Response.ok().build();
	}
}
