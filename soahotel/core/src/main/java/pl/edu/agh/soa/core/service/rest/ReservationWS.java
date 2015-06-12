package pl.edu.agh.soa.core.service.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pl.edu.agh.soa.core.bean.*;
import pl.edu.agh.soa.core.service.ReservationService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Stateless
@Path("/reservation")
public class ReservationWS {


	@EJB
	ReservationService reservationService;

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
		return Response.ok(reservations, MediaType.APPLICATION_JSON).build();
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
		reservation.setStartDate(new Date(34323234));
		reservation.setEndDate(new Date(34323300));
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
