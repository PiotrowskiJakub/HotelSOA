package pl.edu.agh.soa.core.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pl.edu.agh.soa.core.bean.Hotel;
import pl.edu.agh.soa.core.bean.Room;
import pl.edu.agh.soa.core.bean.RoomType;
import pl.edu.agh.soa.core.dao.HotelDAO;
import pl.edu.agh.soa.core.service.HotelService;

/**
 * @author Piotr Konsek
 *
 */
@Stateless
public class HotelServiceImpl implements HotelService {

	@EJB
	HotelDAO hotelDao;
	
	@Override
	public void createHotel(Hotel hotel) {
		hotelDao.saveHotel(hotel);
	}

	@Override
	public List<Hotel> listHotel() {
		List<Hotel> hotels;
		hotels = hotelDao.list();
		return hotels;
	}

	@Override
	public void addRoom(Room room) {
		hotelDao.addRoom(room);
	}

	@Override
	public void addRoomType(RoomType roomType) {
		hotelDao.addRoomType(roomType);	
	}

	@Override
	public List<Room> listRoom(Long id) {
		return hotelDao.listRoom(id);
	}

	@Override
	public Hotel getHotelById(Long id) {
		return hotelDao.getHotelById(id);
	}

	@Override
	public List<RoomType> getRoomTypes() {
		return hotelDao.getRoomTypes();
	}

	@Override
	public RoomType getRoomTypeById(Long id) {
		return hotelDao.getRoomTypeById(id);
	}

	@Override
	public List<RoomType> getRoomTypes(Long id) {
		return hotelDao.getRoomTypes(id);
	}

	@Override
	public void updateHotel(Hotel hotel) {
		hotelDao.updateHotel(hotel);
	}

	@Override
	public void deleteHotel(Long id) {
		hotelDao.deleteHotel(id);
	}

	@Override
	public Room getRoom(Long roomId) {
		return hotelDao.getRoom(roomId);
	}

	@Override
	public Room getRoomByHotelAndType(Long hotelId, Long roomTypeId) {
		List<Room> rooms = hotelDao.getRoomsByHotelAndType(hotelId, roomTypeId);
		return rooms.get( (int)(Math.random()*1000)  % rooms.size() );
		
	}

}
