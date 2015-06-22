package pl.edu.agh.soa.core.dao.impl;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;

import pl.edu.agh.soa.core.bean.Hotel;
import pl.edu.agh.soa.core.bean.Room;
import pl.edu.agh.soa.core.bean.RoomType;
import pl.edu.agh.soa.core.dao.HotelDAO;

/**
 * @author Piotr Konsek
 *
 */
@Local(value=HotelDAO.class)
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class HotelDAOImpl implements HotelDAO {
	
	@PersistenceContext(unitName="soahoteldb")
	EntityManager em;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void saveHotel(Hotel hotel) {
		em.merge(hotel);
		em.flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Hotel> list() {
		Session session = (Session) em.getDelegate();
		return (List<Hotel>) session.createSQLQuery("select h.* from soahotel.hotel h").addEntity(Hotel.class).list();
	}

	@Override
	public void addRoom(Room room) {
		em.merge(room);
	}

	@Override
	public void addRoomType(RoomType roomType) {
		em.persist(roomType);
		em.flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Room> listRoom(Long id) {
		Session session = (Session) em.getDelegate();
		return (List<Room>) session.createSQLQuery("select r.* from soahotel.room r where roo_hot_id=" + id).addEntity(Room.class).list();
	}

	@Override
	public Hotel getHotelById(Long id) {
		return em.find(Hotel.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoomType> getRoomTypes() {	
		Session session = (Session) em.getDelegate();
		return (List<RoomType>) session.createSQLQuery("select rt.* from soahotel.room_types rt").addEntity(RoomType.class).list();
	}

	@Override
	public RoomType getRoomTypeById(Long id) {
		return em.find(RoomType.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoomType> getRoomTypes(Long hotelID) {
		Session session = (Session) em.getDelegate();
		return session.createSQLQuery(""
				+ "select distinct rt.* from soahotel.room_types rt "
				+ "inner join soahotel.room r on r.roo_rty_id = rt.rty_id "
				+ "inner join soahotel.hotel h on h.hot_id = r.roo_hot_id where h.hot_id = " + hotelID).addEntity(RoomType.class).list();
	}

	@Override
	public void updateHotel(Hotel hotel) {
		em.merge(hotel);
	}

	@Override
	public void deleteHotel(Long id) {
		em.remove(em.find(Hotel.class, id));
	}

	@Override
	public Room getRoom(Long roomId) {
		return em.find(Room.class, roomId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Room> getRoomsByHotelAndType(Long hotelId, Long roomTypeId) {
		Session session = (Session) em.getDelegate();
		return session.createSQLQuery("select roo.* from soahotel.room roo where roo.roo_hot_id = " + hotelId +
									" and roo.roo_rty_id = " + roomTypeId).addEntity(Room.class).list();
	}
}
