package pl.edu.agh.soa.core.service.rest;

import pl.edu.agh.soa.core.bean.Complaint;
import pl.edu.agh.soa.core.bean.Reservation;
import pl.edu.agh.soa.core.dto.Mail;
import pl.edu.agh.soa.core.interceptor.CheckToken;
import pl.edu.agh.soa.core.service.ComplaintService;
import pl.edu.agh.soa.core.service.ReservationService;

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
    @EJB
    ReservationService reservationService;

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
    @Path("/complaint/reservation/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @CheckToken
    public Response getComplaintByReservationId(@PathParam("id") Long id, @Context HttpServletRequest request){
        Complaint complaint = complaintService.getComplaintByReservationId(id);
        if(complaint == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        else
            return Response.ok(complaint, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Path("/complaint/reservation/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @CheckToken
    public Response createComplaintWithReservationId(Complaint complaint, @PathParam("id") Long id,@Context HttpServletRequest request) {
        Reservation reservation = reservationService.getReservation(id);
        complaint.setReservation(reservation);
        complaintService.createComplaint(complaint);
        return Response.ok().build();
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
    
    @POST
    @Path("/mail")
    @Consumes(MediaType.APPLICATION_JSON)
    @CheckToken
    public Response sendMail(Mail mail, @Context HttpServletRequest request) {
        complaintService.sendMailToUser(mail);
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
