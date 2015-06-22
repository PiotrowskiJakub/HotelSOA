package pl.edu.agh.soa.core.exampleVendor;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import pl.edu.agh.soa.core.exampleVendor.classes.AncillaryService;
import pl.edu.agh.soa.core.exampleVendor.classes.Hotel;
import pl.edu.agh.soa.core.exampleVendor.classes.Room;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;


/**
 * Created by Ala Czyz.
 */
public class AdapterConsumer {
//    public static final String BASE_URL = "http://localhost:8082/core-0.1/addVendor";
    public static final String BASE_URL = "https://soahotelcore-hotelcore.rhcloud.com/core-0.1/addVendor";
    public static final String VENDOR_COUNTRY = "POLAND";
    public static final String VENDOR_PHONE = "126848986";

    static DataProvider dataProvider = new DataProvider();
    static ClientConfig clientConfig;
    static Client client;
    static WebResource webResource;

    private static Long hotelId;

    public static void main(String[] args) {
        clientConfig = new DefaultClientConfig();
        clientConfig.getClasses().add(JacksonJsonProvider.class);
        client = Client.create(clientConfig);
        webResource = client.resource(BASE_URL);

        translateHotel(dataProvider.getHotel1());
        translateHotel(dataProvider.getHotel2());
    }

    private static void translateHotel(Hotel hotel) {
        createHotel(hotel);

        Long roomTypeId;
        for (Room room : hotel.getRooms()) {
            roomTypeId = createRoomType(room);
            creatRoom(room, roomTypeId);
        }
        createAdditionalServices(hotel.getAncillaryServices());
    }

    private static void createAdditionalServices(List<AncillaryService> ancillaryServices) {
        for (AncillaryService ancillaryService : ancillaryServices) {
            createAdditionalService(ancillaryService);
        }
    }

    private static void createAdditionalService(AncillaryService ancillaryService) {
        ClientResponse clientResponse = webResource
                .path("/addservice")
                .queryParam("name", ancillaryService.getId())
                .queryParam("price", ancillaryService.getPrice())
                .queryParam("desc", ancillaryService.getDesc())
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class);
        String output = clientResponse.getEntity(String.class);
    }

    private static void creatRoom(Room room, Long roomTypeId) {
        ClientResponse clientResponse = webResource
                .path("/room")
                .queryParam("type_id", String.valueOf(roomTypeId))
                .queryParam("hotel_id", String.valueOf(hotelId))
                .queryParam("size", room.getSize())
                .queryParam("number", room.getRoomNumber())
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class);
        String output = clientResponse.getEntity(String.class);
    }

    private static void createHotel(Hotel hotel) {
        ClientResponse clientResponse = webResource
                .path("/hotel")
                .queryParam("country", VENDOR_COUNTRY)
                .queryParam("city", hotel.getCity())
                .queryParam("street", hotel.getStreet())
                .queryParam("house_number", hotel.getNumber())
                .queryParam("code", hotel.getPostalCode())
                .queryParam("phone", VENDOR_PHONE)
                .queryParam("mail", hotel.getEmail())
                .queryParam("name", hotel.getHotelName())
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class);

        String output = clientResponse.getEntity(String.class);
        hotelId = Long.valueOf(output);
        System.out.println("hotel "+hotelId);
    }

    private static Long createRoomType(Room room) {
        ClientResponse clientResponse = webResource
                .path("/roomType")
                .queryParam("name", room.getRoomType())
                .queryParam("price", room.getRoomPrice())
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class);

        String output = clientResponse.getEntity(String.class);
        return Long.valueOf(output);
    }
}
