package pl.edu.agh.soa.core.dao;

import java.util.List;

import javax.ejb.Local;

import pl.edu.agh.soa.core.bean.Hotel;
import pl.edu.agh.soa.core.bean.Room;
import pl.edu.agh.soa.core.bean.RoomType;

@Local
public interface HotelDAO {
	public void saveHotel(Hotel hotel);
	public List<Hotel> list();
	public void addRoom(Room room);
	public void addRoomType(RoomType roomType);
	public List<Room> listRoom(Long id);
}
