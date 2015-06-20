package pl.edu.agh.soa.core.service.rest;

import pl.edu.agh.soa.core.bean.Complaint;
import pl.edu.agh.soa.core.interceptor.CheckToken;
import pl.edu.agh.soa.core.service.ComplaintService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by agnieszkaszczurek on 20.06.15.
 */

@Stateless
@Path("/complaint")
public class ComplaintWS {

    @EJB
    ComplaintService complaintService;

    @POST
    @Path("/complaint")
    @Consumes(MediaType.APPLICATION_JSON)
    @CheckToken
    public Response createComplaint(Complaint complaint, @Context HttpServletRequest request) {
        complaintService.createComplaint(complaint);
        return Response.ok().build();
    }

    @GET
    @Path("/complaint/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @CheckToken
    public Response getComplaintById(@PathParam("id") Long id,@Context HttpServletRequest request) {
        Complaint complaint = complaintService.getComplaint(id);
        if(complaint == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        else
            return Response.ok(complaint, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/complaints")
    @Produces(MediaType.APPLICATION_JSON)
    @CheckToken
    public Response getAllComplaints(@Context HttpServletRequest request) {
        List<Complaint> complaints = complaintService.getAllComplaints();
        return Response.ok(complaints,MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Path("/complaint")
    @Consumes(MediaType.APPLICATION_JSON)
    @CheckToken
    public Response updateComplaint(Complaint complaint, @Context HttpServletRequest request) {
        complaintService.updateComplaint(complaint);
        return Response.ok().build();
    }

    @DELETE
    @Path("/complaint/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @CheckToken
    public Response deleteComplaint(@PathParam("id") Long id, @Context HttpServletRequest request) {
        complaintService.deleteComplaint(id);
        return Response.ok().build();
    }

    @GET
    @Path("/mock")
    public Response mockComplaint() {
        Complaint complaint = new Complaint();
        complaint.setDescription("W ogóle badziew totalny!");

        complaintService.createComplaint(complaint);
        return Response.ok().build();
    }
}
