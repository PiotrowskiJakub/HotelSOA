package pl.edu.agh.soa.core.exampleVendor.classes;

/**
 * Created by Ala Czyz.
 */
public class Room {
    String roomNumber;
    String roomType;
    String size;
    String roomPrice;

    public Room(String roomNumber, String roomType, String size, String roomPrice) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.size = size;
        this.roomPrice = roomPrice;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getSize() {
        return size;
    }

    public String getRoomPrice() {
        return roomPrice;
    }
}
