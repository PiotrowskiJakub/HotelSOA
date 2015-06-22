package pl.edu.agh.soa.core.service.rest;

import pl.edu.agh.soa.core.bean.AdditionalServiceType;
import pl.edu.agh.soa.core.bean.Address;
import pl.edu.agh.soa.core.bean.Contact;
import pl.edu.agh.soa.core.bean.Hotel;
import pl.edu.agh.soa.core.bean.Room;
import pl.edu.agh.soa.core.bean.RoomType;
import pl.edu.agh.soa.core.service.AstService;
import pl.edu.agh.soa.core.service.HotelService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
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
@Path("addVendor")
public class HotelVendorAdapterWS {
    @EJB
    HotelService hotelService;
    @EJB
    AstService astService;

    private static  final String STANDARD_DESC = "standard desc";

    private static  final String STANDARD_PHONE = "000000000000";
    private static final String STANDARD_FLOOR = "0";

    // http://localhost:8082/core-0.1/addVendor/hotel?city=someCity&street=someStreet&country=someCountry&house_number=43&mail=test@test.com&name=someName&code=12123&phone=414154564&desc=dajkfjdskfjdkl
    @POST
    @Path("/hotel")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addHotelInfo(@QueryParam("country") String country,
                                 @QueryParam("city") String city,
                                 @QueryParam("street") String street,
                                 @QueryParam("house_number") String houseNumber,
                                 @QueryParam("code") String postalCode,
                                 @QueryParam("lat") String  latitude,
                                 @QueryParam("log") String  longtitude,
                                 @QueryParam("phone") String phone,
                                 @QueryParam("mail") String mail,
                                 @QueryParam("name") String name,
                                 @QueryParam("desc") String desc) {
        if (country == null || city == null || street == null || houseNumber == null || postalCode == null || mail==null || name == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("coutry, city, street, house number and postal code, mail and name are obligatoty").type("text/plain").build();
        }

        Address address = new Address();
        address.setCountry(country);
        address.setCity(city);
        address.setStreet(street);
        address.setHouseNumber(houseNumber);
        address.setPostalCode(postalCode);
        address.setLatitude(Double.valueOf(latitude));
        address.setLongitude(Double.valueOf(longtitude));

        if(phone == null)
            phone = STANDARD_PHONE;


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


    //http://localhost:8082/core-0.1/addVendor/roomType?price=250&desc=dfsjkdsjak&name=Superowy
    @POST
    @Path("/roomType")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRoomTypeToHotel(@QueryParam("price") String price,
                                       @QueryParam("name") String name,
                                       @QueryParam("desc") String desc) {
        if(name == null || price ==null){
            return Response.status(Response.Status.BAD_REQUEST).entity("Name and price is obligatory").type("text/plain").build();
        }
        if(desc == null){
            desc = STANDARD_DESC;
        }


        RoomType roomType = new RoomType();

        roomType.setPrice(new BigDecimal(price));
        roomType.setName(name);
        roomType.setDescription(desc);

        hotelService.addRoomType(roomType);

        return Response.ok(roomType).build();
    }


//    http://localhost:8082/core-0.1/addVendor/room?type_id=59&hotel_id=58&number=2154&size=3&floor=8

    @POST
    @Path("/room")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRoomToHotel(@QueryParam("type_id") Long roomTypeId,
                                   @QueryParam("hotel_id") Long hotelId,
                                   @QueryParam("size") Integer size,
                                   @QueryParam("floor") String floor,
                                   @QueryParam("number") String number) {
        if(roomTypeId == null || hotelId ==null || size == null || number == null){
            return Response.status(Response.Status.BAD_REQUEST).entity("room type id, hotel id, size and number is obligatory").type("text/plain").build();
        }
        if(floor == null)
            floor = STANDARD_FLOOR;

        Room room = new Room();
        room.setHotel(hotelService.getHotelById(hotelId));
        room.setRoomType(hotelService.getRoomTypeById(roomTypeId));
        room.setSize(size);
        room.setFloor(Integer.valueOf(floor));
        room.setNumber(Integer.valueOf(number));

        hotelService.addRoom(room);
        return Response.ok(room).build();
    }

//    http://localhost:8082/core-0.1/addVendor/addservice?price=250&name=Superowy
    @POST
    @Path("/addservice")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAdditionalServices(@QueryParam("price") String price,
                                          @QueryParam("name") String name,
                                          @QueryParam("desc") String desc) {
        if(name == null || price == null){
            return Response.status(Response.Status.BAD_REQUEST).entity("Name and price is obligatory").type("text/plain").build();
        }
        if(desc == null){
            desc = STANDARD_DESC;
        }
        AdditionalServiceType additionalServiceType = new AdditionalServiceType();
        additionalServiceType.setPrice(new BigDecimal(price));
        additionalServiceType.setName(name);
        additionalServiceType.setDescription(desc);

        astService.add(additionalServiceType);
        return Response.ok(additionalServiceType).build();
    }

}
