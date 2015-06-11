package pl.edu.agh.soa.core.service;

import java.util.List;

import javax.ejb.Remote;

import pl.edu.agh.soa.core.bean.Hotel;
import pl.edu.agh.soa.core.bean.Room;
import pl.edu.agh.soa.core.bean.RoomType;

@Remote
public interface HotelService {
	
	/**save given hotel to db
	 * 
	 * @param hotel
	 * @return
	 */
	public void createHotel(Hotel hotel);
	
	/**
	 * @return list of all hotels
	 */
	public List<Hotel> listHotel();

	/**save room
	 * 
	 * @param room
	 */
	public void addRoom(Room room);

	/**save room type
	 * 
	 * @param roomType
	 */
	public void addRoomType(RoomType roomType);

	/**
	 * @param id
	 * @return list of room in hotel with given id
	 */
	public List<Room> listRoom(Long id);

	public Hotel getHotelById(Long parseLong);

	public List<RoomType> getRoomTypes();

	public RoomType getRoomTypeById(Long parseLong);
}
