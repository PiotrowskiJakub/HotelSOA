package pl.edu.agh.soa.core.service.rest;

import java.io.File;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import pl.edu.agh.soa.core.service.RaportService;

@Stateless
@Path("raport")
public class RaportWS {

	@EJB
	RaportService raportService;
	
	@GET
	@Path("{hotelId}")
	@Produces("application/pdf")
	public Response getPDF(@PathParam("hotelId") Long hotelId) {
		File file = raportService.generateHotelReservationsRaport(hotelId);
		Response.ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition",
				"attachment; filename=\"Raport_rezerwacji.pdf\"");
		return response.build();
	}
}
