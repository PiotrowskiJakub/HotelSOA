package pl.edu.agh.soa.ba.form;

import pl.edu.agh.soa.core.bean.Hotel;
import pl.edu.agh.soa.core.bean.Room;
import pl.edu.agh.soa.core.bean.RoomType;

/**
 * @author Piotr Konsek
 *
 */
public class RoomForm {
	private Room room;
	private String roomTypeID;
	
	public RoomForm(){
		room = new Room();
	}
	
	public String getRoomTypeID() {
		return roomTypeID;
	}

	public void setRoomTypeID(String roomTypeID) {
		this.roomTypeID = roomTypeID;
	}

	public Integer getFloor() {
		return room.getFloor();
	}

	public Hotel getHotel() {
		return room.getHotel();
	}

	public Integer getNumber() {
		return room.getNumber();
	}

	public RoomType getRoomType() {
		return room.getRoomType();
	}

	public Integer getSize() {
		return room.getSize();
	}

	public void setFloor(Integer floor) {
		room.setFloor(floor);
	}

	public void setHotel(Hotel hotel) {
		room.setHotel(hotel);
	}

	public void setNumber(Integer number) {
		room.setNumber(number);
	}

	public void setRoomType(RoomType roomType) {
		room.setRoomType(roomType);
	}

	public void setSize(Integer size) {
		room.setSize(size);
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
	
}
