package pl.edu.agh.soa.core.service.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pl.edu.agh.soa.core.bean.Hotel;
import pl.edu.agh.soa.core.bean.Room;
import pl.edu.agh.soa.core.bean.RoomType;
import pl.edu.agh.soa.core.service.HotelService;

@Stateless
@Path("/hotel")
public class HotelWS {

	@EJB
	HotelService hotelService;
	
	@POST
	@Path("/hotel")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createHotel(Hotel hotel) {
		hotelService.createHotel(hotel);
		return Response.ok().build();
	}

	@GET
	@Path("/hotels")
	public Response listHotel() {
		List<Hotel> hotels = hotelService.listHotel();
		return Response.ok(hotels).build();
	}
	
	@GET
	@Path("/rooms/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listRooms(@PathParam("id") Long id){
		List<Room> rooms = hotelService.listRoom(id);
		return Response.ok(rooms, MediaType.APPLICATION_JSON).build();
	}

	@POST
	@Path("/room")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createRoom(Room room){
		hotelService.addRoom(room);	
		return Response.ok().build();
	}
	
	@POST
	@Path("/roomType")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createRoomType(RoomType roomType){
		hotelService.addRoomType(roomType);
		return Response.ok().build();
	}
}
