package pl.edu.agh.soa.core.service.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pl.edu.agh.soa.core.interceptor.CheckToken;
import pl.edu.agh.soa.core.service.InvoiceService;

@Stateless
@Path("invoices")
public class InvoiceWS {

	@EJB
	InvoiceService invoiceService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CheckToken
	public Response getInvoices(@Context HttpServletRequest request) {
		return Response.ok(invoiceService.getInvoices()).build();
	}

	@GET
	@Path("/user/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	@CheckToken
	public Response getUserInvoices(@PathParam("userId") Long userId,  @Context HttpServletRequest request) {
		return Response.ok(invoiceService.getUserInvoices(userId)).build();
	}

	@GET
	@Path("/{invoiceId}")
	@Produces("application/pdf")
	@CheckToken
	public Response getInvoiceFile(@PathParam("invoiceId") Long invoiceId,  @Context HttpServletRequest request) {
		Response.ResponseBuilder response = Response.ok(invoiceService.getInvoiceFile(invoiceId));
		response.header("Content-Disposition",
				"attachment; filename=\"Faktura.pdf\"");
		return response.build();
	}
	
	@POST
	@Path("/{reservationID}")
	@Produces(MediaType.TEXT_PLAIN)
	@CheckToken
	public Response generateInvoice(@PathParam("reservationID") Long reservationID, @Context HttpServletRequest request) {
		invoiceService.generateInvoice(reservationID);
		return Response.ok("Invoice generated!").build();
	}
}
