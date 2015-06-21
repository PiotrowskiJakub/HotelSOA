package pl.edu.agh.soa.core.service.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pl.edu.agh.soa.core.bean.AdditionalServiceType;
import pl.edu.agh.soa.core.interceptor.CheckToken;
import pl.edu.agh.soa.core.service.AstService;

@Stateless
@Path("ast")
public class AstRest {

	@EJB
	private AstService astService;

	@CheckToken
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAst(@Context HttpServletRequest request) {
		AdditionalServiceType ast = astService.getAst();

		return Response.ok(ast).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addAst(AdditionalServiceType ast) {
		astService.add(ast);
	}
}
