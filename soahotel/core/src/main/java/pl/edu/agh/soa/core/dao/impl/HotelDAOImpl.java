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
		em.persist(hotel);
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
	public RoomType getRoomById(Long id) {
		return em.find(RoomType.class, id);
	}
}
