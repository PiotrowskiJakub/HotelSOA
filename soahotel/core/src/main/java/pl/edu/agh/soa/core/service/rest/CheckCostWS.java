package pl.edu.agh.soa.core.service.rest;

import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pl.edu.agh.soa.core.service.CheckCostService;

/**
 * Created by Ala Czyz.
 */
@Stateless
@Path("/reservation")
public class CheckCostWS {
    @EJB
    CheckCostService checkCostService;

    @GET
    @Path("/{id}/cost")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReservation(@PathParam("id") Long reservationId) {
        BigDecimal price = checkCostService.getCurrentCost(reservationId);
        return Response.ok(price, MediaType.APPLICATION_JSON).build();
    }
}
