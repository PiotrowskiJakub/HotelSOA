package pl.edu.agh.soa.core.service.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pl.edu.agh.soa.core.bean.AdditionalServiceType;
import pl.edu.agh.soa.core.service.AstService;

@Stateless
@Path("ast")
public class AstRest {

	@EJB
	private AstService astService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public AdditionalServiceType getAst() {
		AdditionalServiceType ast = astService.getAst();

		return ast;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addAst(AdditionalServiceType ast) {
		astService.add(ast);
	}
}
