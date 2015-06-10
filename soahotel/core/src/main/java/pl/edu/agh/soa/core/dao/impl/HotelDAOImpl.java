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

import pl.edu.agh.soa.core.bean.Account;
import pl.edu.agh.soa.core.bean.Contact;
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
		return (List<Hotel>) session.createSQLQuery("select {h.*},{a.*},{c.*} from soahotel.hotel as h natural join soahotel.address as a natural join soahotel.contact as c").addEntity(Hotel.class).addEntity(Account.class).addEntity(Contact.class).list();
	}

	@Override
	public void addRoom(Room room) {
		em.persist(room);
	}

	@Override
	public void addRoomType(RoomType roomType) {
		em.persist(roomType);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Room> listRoom(Long id) {
		Session session = (Session) em.getDelegate();
		return (List<Room>) session.createSQLQuery("select {r.*},{h.*},{rt.*} from soahotel.room as r natural join soahotel.hotel as h natural join soahotel.room_type as rt").addEntity(Room.class).addEntity(Hotel.class).addEntity(RoomType.class).list();
	}
}
