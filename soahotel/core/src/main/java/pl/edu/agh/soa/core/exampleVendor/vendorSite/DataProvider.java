package pl.edu.agh.soa.core.exampleVendor.vendorSite;

import pl.edu.agh.soa.core.exampleVendor.vendorSite.classes.AncillaryServices;
import pl.edu.agh.soa.core.exampleVendor.vendorSite.classes.Hotel;
import pl.edu.agh.soa.core.exampleVendor.vendorSite.classes.Room;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ala Czyz.
 */
public class DataProvider {


    Hotel hotel1;
    Hotel hotel2;


    public DataProvider() {
        initateHotel1();
        initateHotel2();

    }


    private void initateHotel1() {
        hotel1 = new Hotel();
        hotel1.setHotelName("Nad potokiem");
        hotel1.setCity("Warszawa");
        hotel1.setStreet("hotelarska");
        hotel1.setNumber("123");
        hotel1.setPostalCode("12546");
        hotel1.setEmail("awesome@mail.com");

        hotel1.setAncillaryServices(Arrays.asList(new AncillaryServices( "12.00", "12", "sniadanie"),
                new AncillaryServices( "123.00", "13", "sauna"),
                new AncillaryServices( "13.00", "14", "wino")));

        hotel1.setRooms(Arrays.asList(new Room("1001", "double", "2",  "120.00"),
                new Room("1003", "double&Infant", "2",  "150.00"),
                new Room("1005", "family", "6",  "520.00"),
                new Room("1004", "twister", "2",  "130.00")
        ));
    }

    private void initateHotel2() {
        hotel2 = new Hotel();
        hotel2.setHotelName("W gorach");
        hotel2.setCity("Krynica");
        hotel2.setStreet("wypoczynkowa");
        hotel2.setNumber("12");
        hotel2.setPostalCode("18546");
        hotel2.setEmail("awesome2@mail.com");

        hotel2.setAncillaryServices(Arrays.asList(new AncillaryServices( "12.00", "18", "kolacja"),
                new AncillaryServices( "24.00", "20", "woda zdrojowa"),
                new AncillaryServices( "16.00", "19", "szachy")));

        hotel2.setRooms(Arrays.asList(new Room("2001", "double", "2",  "100.00"),
                new Room("2005", "family", "6",  "420.00"),
                new Room("2004", "twister", "2",  "110.00")
        ));

    }

    public Hotel getHotel1() {
        return hotel1;
    }

    public Hotel getHotel2() {
        return hotel2;
    }


}
