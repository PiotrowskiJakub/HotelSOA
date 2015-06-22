package pl.edu.agh.soa.core.exampleVendor.vendorSite;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import pl.edu.agh.soa.core.exampleVendor.vendorSite.classes.Hotel;
import pl.edu.agh.soa.core.exampleVendor.vendorSite.classes.Room;


/**
 * Created by Ala Czyz.
 */
public class AdapterConsumer {
    public static final String BASE_URL = "http://localhost:8082/core-0.1/addVendor";
    public static final String VENDOR_COUNTRY = "POLAND";
    public static final String VENDOR_PHONE = "126848986";

    static DataProvider dataProvider = new DataProvider();
    static ClientConfig clientConfig;
    static Client client;
    static WebResource webResource;

    private String  hotel_id;

    public static void main(String[] args) {


        clientConfig = new DefaultClientConfig();
        clientConfig.getClasses().add(JacksonJsonProvider.class);
        client = Client.create(clientConfig);
        webResource = client.resource(BASE_URL);

        translateHotel(dataProvider.getHotel1());


        ClientResponse clientResponse = webResource
                .path("/roomType")
                .queryParam("name", "awesomeName")
                .queryParam("price", "123.00")
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class);

        String output = clientResponse.getEntity(String.class);
        System.out.println(output);
    }

    private static void translateHotel(Hotel hotel) {
        createHotel(hotel);

        for (Room room : hotel.getRooms()) {
            createRoomType(room);
        }


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
        System.out.println(output);
    }

    private static void createRoomType(Room room) {
        ClientResponse clientResponse = webResource
                .path("/roomType")
                .queryParam("name", room.getRoomType())
                .queryParam("price", room.getRoomPrice())
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class);

        String output = clientResponse.getEntity(String.class);
//        System.out.println(output);
    }
}
