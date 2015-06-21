package pl.edu.agh.soa.core.service.rest;

import pl.edu.agh.soa.core.bean.AdditionalServiceType;
import pl.edu.agh.soa.core.bean.Address;
import pl.edu.agh.soa.core.bean.Contact;
import pl.edu.agh.soa.core.bean.Hotel;
import pl.edu.agh.soa.core.bean.Room;
import pl.edu.agh.soa.core.bean.RoomType;
import pl.edu.agh.soa.core.dao.HotelDAO;
import pl.edu.agh.soa.core.service.AstService;
import pl.edu.agh.soa.core.service.HotelService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.print.DocFlavor;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Ala Czyz.
 */
@Stateless
@Path("addVendor")
public class HotelVendorAdapterWS {
    @EJB
    HotelService hotelService;

    @EJB
    AstService astService;

    // http://localhost:8082/core-0.1/addVendor/hotel?city=someCity&street=someStreet&country=someCountry&house_number=43&mail=test@test.com&name=someName&code=12123&phone=414154564&desc=dajkfjdskfjdkl
    @POST
    @Path("/hotel")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addHotelInfo(@QueryParam("country") String country,
                                 @QueryParam("city") String city,
                                 @QueryParam("street") String street,
                                 @QueryParam("house_number") String houseNumber,
                                 @QueryParam("code") String postalCode,
                                 @QueryParam("lat") Double latitude,
                                 @QueryParam("log") Double longtitude,
                                 @QueryParam("phone") String phone,
                                 @QueryParam("mail") String mail,
                                 @QueryParam("name") String name,
                                 @QueryParam("desc") String desc) {
        Address address = new Address();
        address.setCountry(country);
        address.setCity(city);
        address.setStreet(street);
        address.setHouseNumber(houseNumber);
        address.setPostalCode(postalCode);
        address.setLatitude(latitude);
        address.setLongitude(longtitude);

        Contact contact = new Contact();
        contact.setPhone(phone);
        contact.setMail(mail);

        Hotel hotel = new Hotel();
        hotel.setAddress(address);
        hotel.setContact(contact);
        hotel.setName(name);
        hotel.setDesc(desc);

        hotelService.createHotel(hotel);
        return Response.ok(hotel).build();
    }


    @POST
    @Path("/roomType")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRoomTypeToHotel(@QueryParam("price") BigDecimal price,
                                       @QueryParam("name") String name,
                                       @QueryParam("desc") String desc) {
        RoomType roomType = new RoomType();

        roomType.setPrice(price);
        roomType.setName(name);
        roomType.setDescription(desc);

        hotelService.addRoomType(roomType);
        return Response.ok(roomType).build();
    }

    @POST
    @Path("/room")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRoomToHotel(@QueryParam("type_id") Long roomTypeId,
                                   @QueryParam("hotel_id") Long hotelId,
                                   @QueryParam("size") Integer size,
                                   @QueryParam("floor") Integer floor,
                                   @QueryParam("number") Integer number) {

        Room room = new Room();
        room.setHotel(hotelService.getHotelById(hotelId));
        room.setRoomType(hotelService.getRoomTypeById(roomTypeId));
        room.setSize(size);
        room.setFloor(floor);
        room.setNumber(number);

        hotelService.addRoom(room);
        return Response.ok(room).build();
    }

    @POST
    @Path("/addservice")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAdditionalServices(@QueryParam("price") BigDecimal price,
                                          @QueryParam("name") String name,
                                          @QueryParam("desc") String desc) {
        AdditionalServiceType additionalServiceType = new AdditionalServiceType();
        additionalServiceType.setPrice(price);
        additionalServiceType.setName(name);
        additionalServiceType.setDescription(desc);

        astService.add(additionalServiceType);
        return Response.ok(additionalServiceType).build();
    }

}
