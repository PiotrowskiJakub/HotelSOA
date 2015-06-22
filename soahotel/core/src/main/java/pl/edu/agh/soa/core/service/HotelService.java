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
	public Long createHotel(Hotel hotel);
	
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
	public Long addRoomType(RoomType roomType);

	/**
	 * @param id
	 * @return list of room in hotel with given id
	 */
	public List<Room> listRoom(Long id);

	public Hotel getHotelById(Long parseLong);

	public List<RoomType> getRoomTypes();
	
	public List<RoomType> getRoomTypes(Long id);

	public RoomType getRoomTypeById(Long id);

	public void updateHotel(Hotel hotel);

	public void deleteHotel(Long id);

	public Room getRoom(Long roomId);

	public Room getRoomByHotelAndType(Long hotelId, Long roomTypeId);
}
