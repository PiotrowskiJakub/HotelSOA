package pl.edu.agh.soa.core.service.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pl.edu.agh.soa.core.bean.Account;
import pl.edu.agh.soa.core.bean.Token;
import pl.edu.agh.soa.core.dao.AccountDAO;
import pl.edu.agh.soa.core.service.LoginService;

@Stateless
@Path("/login")
public class LoginWS {
	
	@EJB
	AccountDAO accountDao;
	
	@EJB
	LoginService loginService;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)	
	@Path("/in/{mail}/{password}")
	public Response login(@PathParam("mail") String mail, @PathParam("password") String password, @Context HttpServletRequest request){
//		HttpSession session = request.getSession();
		Account account = accountDao.getAccount(mail);
		if(account == null)
			return Response.status(Response.Status.UNAUTHORIZED.getStatusCode()).entity("There is no user with this login!").build();
		if(!account.getPassword().equals(password))
			return Response.status(Response.Status.UNAUTHORIZED.getStatusCode()).entity("Wrong password").build();
//		session.setAttribute("LOGIN", "OK");
		Token token = loginService.createToken();
		
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("token", token.getToken());
		responseMap.put("account", account);
		return Response.ok().entity(responseMap).build();
	}
	
	@POST
	@Path("/out")
	public Response logout(){
		return Response.ok().build();
	}
}
