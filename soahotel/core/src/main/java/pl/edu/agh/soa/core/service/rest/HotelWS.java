package pl.edu.agh.soa.core.service.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pl.edu.agh.soa.core.bean.Hotel;
import pl.edu.agh.soa.core.bean.Room;
import pl.edu.agh.soa.core.bean.RoomType;
import pl.edu.agh.soa.core.interceptor.CheckToken;
import pl.edu.agh.soa.core.service.HotelService;

@Stateless
@Path("/hotel")
public class HotelWS {

	@EJB
	HotelService hotelService;
	
	@POST
	@Path("/hotel")
	@Consumes(MediaType.APPLICATION_JSON)
	@CheckToken
	public Response createHotel(Hotel hotel, @Context HttpServletRequest request) {
		hotelService.createHotel(hotel);
		return Response.ok().build();
	}
	
	@GET
	@Path("/hotel/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@CheckToken
	public Response getHotelById(@PathParam("id") String id, @Context HttpServletRequest request){
		Hotel hotel = hotelService.getHotelById(Long.parseLong(id));
		if(hotel == null)
			return Response.status(Response.Status.NOT_FOUND).build();
		else
			return Response.ok(hotel, MediaType.APPLICATION_JSON).build();	
	}

	@GET
	@Path("/hotels")
	@CheckToken
	public Response listHotel(@Context HttpServletRequest request) {
		List<Hotel> hotels = hotelService.listHotel();
		return Response.ok(hotels, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/rooms/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@CheckToken
	public Response listRooms(@PathParam("id") Long id, @Context HttpServletRequest request){
		List<Room> rooms = hotelService.listRoom(id);
		return Response.ok(rooms, MediaType.APPLICATION_JSON).build();
	}

	@POST
	@Path("/room")
	@Consumes(MediaType.APPLICATION_JSON)
	@CheckToken
	public Response createRoom(Room room, @Context HttpServletRequest request){
		hotelService.addRoom(room);	
		return Response.ok().build();
	}
	
	@POST
	@Path("/roomType")
	@Consumes(MediaType.APPLICATION_JSON)
	@CheckToken
	public Response createRoomType(RoomType roomType, @Context HttpServletRequest request){
		hotelService.addRoomType(roomType);
		return Response.ok().build();
	}
	
	@GET
	@Path("/roomType/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@CheckToken
	public Response getRoomType(@PathParam("id") String id, @Context HttpServletRequest request){
		RoomType roomType = hotelService.getRoomTypeById(Long.parseLong(id));
		if(roomType == null)
			return Response.status(Response.Status.NO_CONTENT).build();
		else
			return Response.ok(roomType, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/roomTypes")
	@Produces(MediaType.APPLICATION_JSON)
	@CheckToken
	public Response getRoomTypes(@Context HttpServletRequest request){
		List<RoomType> roomTypes = hotelService.getRoomTypes();
		if(roomTypes == null) 
			return Response.status(Response.Status.NOT_FOUND).build();
		else
			return Response.ok(roomTypes, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/roomTypes/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@CheckToken
	public Response getRoomTypesInHotelById(@PathParam("id") String id, @Context HttpServletRequest request){
		List<RoomType> roomTypes = hotelService.getRoomTypes(Long.parseLong(id));
		if(roomTypes == null) 
			return Response.status(Response.Status.NOT_FOUND).build();
		else
			return Response.ok(roomTypes, MediaType.APPLICATION_JSON).build();
	}
	
	@PUT
	@Path("/hotel")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateHotel(Hotel hotel){
		hotelService.updateHotel(hotel);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/hotel/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteHotel(@PathParam("id") Long id){
		hotelService.deleteHotel(id);
		return Response.ok().build();
	}
}
