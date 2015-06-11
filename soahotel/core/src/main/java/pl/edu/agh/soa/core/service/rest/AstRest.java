package pl.edu.agh.soa.core.service.rest;

import java.util.HashMap;
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

import org.apache.camel.ProducerTemplate;
import org.apache.camel.cdi.CdiCamelContext;
import org.apache.camel.cdi.ContextName;

import pl.edu.agh.soa.core.bean.AdditionalServiceType;
import pl.edu.agh.soa.core.service.AstService;

@Stateless
@Path("ast")
public class AstRest {

	@EJB
	private AstService astService;

	@Inject
	@ContextName("camel-context")
	CdiCamelContext context;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public AdditionalServiceType getAst() {
		AdditionalServiceType ast = astService.getAst();

		// Sample camel proucer
		ProducerTemplate producer = context.createProducerTemplate();
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("To", "qballonix@gmail.com");
		headers.put("Subject", "[SOAHotel] Twoje konto zostało utworzone");
		producer.sendBodyAndHeaders("direct:mail", "Twoje konto w systemie SOAHotel zostało pomyślnie utworzone.\n\nPozdrawiamy,\nZespół SOAHotel", headers);

		return ast;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addAst(AdditionalServiceType ast) {
		astService.add(ast);
	}
}
