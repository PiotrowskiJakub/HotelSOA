package pl.edu.agh.soa.core.service.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pl.edu.agh.soa.core.bean.Account;
import pl.edu.agh.soa.core.bean.Address;
import pl.edu.agh.soa.core.bean.Contact;
import pl.edu.agh.soa.core.bean.DiscountType;
import pl.edu.agh.soa.core.bean.Hotel;
import pl.edu.agh.soa.core.bean.Reservation;
import pl.edu.agh.soa.core.bean.Room;
import pl.edu.agh.soa.core.bean.RoomType;
import pl.edu.agh.soa.core.bean.Termin;
import pl.edu.agh.soa.core.service.ReservationService;

@Stateless
@Path("/reservation")
public class ReservationWS {


	@EJB
	ReservationService reservationService;
	
	@GET
	@Path("/hotel/{id}")
	public Response getHotelReservations(@PathParam("id") Long id){
		List<Reservation> reservations = reservationService.getHotelReservations(id);
		return Response.ok(reservations, MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("/reservation/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReservation(@PathParam("id") Long id) {
		Reservation reservation = reservationService.getReservation(id);
		return Response.ok(reservation, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReservation(){
		List<Reservation> reservations = reservationService.getReservations();
		return Response.ok(reservations, MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("/client/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReservations(@PathParam("id") Long id) {
		List<Reservation> reservationList = reservationService.getReservations(id);
		return Response.ok(reservationList,  MediaType.APPLICATION_JSON).build();
	}
	

	@GET
	@Path("/termins/{id}/{rt}/{year}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTermins(@PathParam("id") Long hotelID ,@PathParam("rt") Long roomTypeID, @PathParam("year") Integer year) {
		List<Reservation> reservations = reservationService.getReservations(hotelID, roomTypeID, year);
		Calendar cal = Calendar.getInstance();
		
		// false - free
		// true - taken
		boolean [] days;
		if((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0)))
            days = new boolean[366];
		else
			days = new boolean [365];
		
		for(Reservation reservation : reservations){
			cal.setTime(reservation.getStartDate());
			int start = cal.get(Calendar.DAY_OF_YEAR);
			cal.setTime(reservation.getEndDate());
			int stop = cal.get(Calendar.DAY_OF_YEAR);
			for(int i=start; i<stop; i++)
				days[i] = true;
		}
		
		List<Termin> termins = new ArrayList<Termin>();
		boolean streak = false;
		Termin termin = null;
		
		cal.set(Calendar.YEAR, year);
		for(int i=0;i<days.length;i++){
			if(days[i] == false && !streak){
				termin = new Termin();
				if(i == 0)
					cal.set(Calendar.DAY_OF_YEAR, i+1);
				else
					cal.set(Calendar.DAY_OF_YEAR, i);
				
				termin.setFrom(new java.sql.Date(cal.getTimeInMillis()));
				streak = true;
			}
			else if( days[i] == true && streak){
				cal.set(Calendar.DAY_OF_YEAR, i);
				termin.setTo(new java.sql.Date(cal.getTimeInMillis()));
				streak = false;
				termins.add(termin);
			}	
			else if( days[i] == false && i == days.length - 1 && streak){
				cal.set(Calendar.DAY_OF_YEAR, i+1);
				termin.setTo(new java.sql.Date(cal.getTimeInMillis()));
				streak = false;
				termins.add(termin);
			}
		}
		
		return Response.ok(termins, MediaType.APPLICATION_JSON).build();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createReservation(Reservation reservation) {
		reservationService.createReservation(reservation);
		return Response.ok().build();
	}

	@PUT
	@Path("/")
	public Response updateReservation(Reservation reservation) {
		reservationService.updateReservation(reservation);
		return Response.ok().build();
	}

	@DELETE
	@Path("reservation/{id}")
	public Response reservationDelete(@PathParam("id") Long id) {
		reservationService.reservationDelete(id);
		return Response.ok().build();
	}

	@GET
	@Path("/mock")
	public Response createMockReservation() {
		Reservation reservation = new Reservation();
		reservation.setAccount(mockAccount());
		reservation.setStartDate(new Date(1433966851));
		reservation.setEndDate(new Date(1435694851));
		reservation.setRoom(mockRoom());
		reservation.setDiscountType(mockDiscountType());
		reservation.setPaid(true);

		reservationService.createReservation(reservation);
		return Response.ok().build();
	}

	private DiscountType mockDiscountType() {

		DiscountType discountType = new DiscountType();
		discountType.setName("Dla  studenta");
		discountType.setDescription("-50%");
		discountType.setPrice(new BigDecimal(1000.00));

		return discountType;
	}

	private Account mockAccount() {
		Account account = new Account();
		account.setAccountStatus("A");
		account.setAccountType("E");
		account.setAddress(mockAddress());
		account.setBirthDate(new Date(12345667));
		account.setContact(mockContact());
		account.setFirstName("Aga");
		account.setLastName("Sz");
		account.setNewsletterPermission(true);
		account.setPassword("aagaa");
		account.setRegulaminAccepted(true);

		return account;
	}

	private Room mockRoom() {

		Room room = new Room();
		room.setHotel(mockHotel());
		room.setRoomType(mockRoomType());
		room.setFloor(10);
		room.setNumber(3);
		room.setSize(50);

		return room;
	}

	private RoomType mockRoomType() {
		RoomType roomType = new RoomType();
		roomType.setName("Piękny");
		roomType.setDescription("Taki pikny");
		roomType.setPrice(new BigDecimal(1500.00));

		return roomType;
	}

	private Hotel mockHotel() {
		Hotel hotel = new Hotel();
		hotel.setAddress(mockAddress());
		hotel.setAverageRate(4.5);
		hotel.setContact(mockContact());
		hotel.setName("NAME");
		return hotel;
	}

	private Contact mockContact() {
		Contact contact = new Contact();
		contact.setFax("999888777");
		contact.setFaxPermission(true);
		contact.setMail("aga@gmail.com");
		contact.setMailPermission(true);
		contact.setPhone("999888777");
		contact.setSmsPermission(true);

		return contact;
	}

	private Address mockAddress() {
		Address address = new Address();
		address.setCity("Krakow");
		address.setCountry("Poland");
		address.setFlatNumber(10);
		address.setHouseNumber("20");
		address.setPostalCode("34567");
		address.setStreet("Opolska");
		address.setState("malopolskie");

		return address;
	}

}
