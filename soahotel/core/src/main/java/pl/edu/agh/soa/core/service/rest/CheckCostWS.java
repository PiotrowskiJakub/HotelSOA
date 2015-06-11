package pl.edu.agh.soa.core.service.rest;

import pl.edu.agh.soa.core.service.CheckCostService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;

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
    public Response getReservation(@QueryParam("id") Long reservationId) {
        BigDecimal price = checkCostService.getCurrentCost(reservationId);
        return Response.ok(price, MediaType.APPLICATION_JSON).build();
    }
}
