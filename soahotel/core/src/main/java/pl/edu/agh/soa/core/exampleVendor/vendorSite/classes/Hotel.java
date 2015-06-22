package pl.edu.agh.soa.core.exampleVendor.vendorSite.classes;


import java.util.List;

/**
 * Created by Ala Czyz.
 */
public class Hotel {
    String hotelName;
    String city;
    String postalCode;
    String street;
    String number;
    String email;

    List<Room> rooms;
    List<AncillaryServices> ancillaryServices;


    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<AncillaryServices> getAncillaryServices() {
        return ancillaryServices;
    }

    public void setAncillaryServices(List<AncillaryServices> ancillaryServices) {
        this.ancillaryServices = ancillaryServices;
    }


}
