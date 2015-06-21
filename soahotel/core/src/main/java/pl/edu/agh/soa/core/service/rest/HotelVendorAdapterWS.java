package pl.edu.agh.soa.core.service.rest;

import pl.edu.agh.soa.core.bean.AdditionalServiceType;
import pl.edu.agh.soa.core.bean.Address;
import pl.edu.agh.soa.core.bean.Contact;
import pl.edu.agh.soa.core.bean.Hotel;
import pl.edu.agh.soa.core.bean.Room;
import pl.edu.agh.soa.core.bean.RoomType;
import pl.edu.agh.soa.core.service.HotelService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Ala Czyz.
 */
@Stateless
@Path("addVendor")
public class HotelVendorAdapterWS {
    @EJB
    HotelService hotelService;


    @POST
    @Path("/hotel")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addHotelInfo(@QueryParam("country") String country,
                                 @QueryParam("city") String city,
                                 @QueryParam("street") String street,
                                 @QueryParam("house_number") String houseNumber,
                                 @QueryParam("lat") Double latitude,
                                 @QueryParam("log") Double longtitude,
                                 @QueryParam("phone") String phone,
                                 @QueryParam("name") String name,
                                 @QueryParam("desc") String desc) {
        Address address = new Address();
        address.setCountry(country);
        address.setCity(city);
        address.setStreet(street);
        address.setHouseNumber(houseNumber);
        address.setLatitude(latitude);
        address.setLongitude(longtitude);

        Contact contact = new Contact();
        contact.setPhone(phone);

        Hotel hotel = new Hotel();
        hotel.setAddress(address);
        hotel.setContact(contact);
        hotel.setName(name);
        hotel.setDesc(desc);
        return  Response.ok(hotel).build();
    }



    @POST
    @Path("/roomType")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRoomTypesToHotel(List<RoomType> roomTypes) {
        return  null;
    }

    @POST
    @Path("/room")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRoomsToHotel(List<Room> rooms) {
        return  null;
    }

    @POST
    @Path("/addservice")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAdditionalServices(AdditionalServiceType additionalServiceType) {
        return  null;
    }

}
