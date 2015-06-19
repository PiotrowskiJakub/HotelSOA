package pl.edu.agh.soa.ba.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.QueryParam;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.edu.agh.soa.ba.form.CreateHotelForm;
import pl.edu.agh.soa.ba.form.RoomForm;
import pl.edu.agh.soa.ba.form.RoomTypeForm;
import pl.edu.agh.soa.core.bean.Address;
import pl.edu.agh.soa.core.bean.Contact;
import pl.edu.agh.soa.core.bean.Hotel;
import pl.edu.agh.soa.core.bean.Reservation;
import pl.edu.agh.soa.core.bean.Room;
import pl.edu.agh.soa.core.bean.RoomType;

@Controller
public class HotelController extends BaseController{

	@RequestMapping(value = "/hotel_management", method = RequestMethod.GET)
	public String hotelMangement(){
		return "hotel_management";
	}

	@RequestMapping(value = "/hotel_create", method = RequestMethod.GET)
	public ModelAndView initialCreateHotel(){
		ModelAndView modelAndView = new ModelAndView("create_hotel");
		CreateHotelForm createHotelForm = new CreateHotelForm();
		createHotelForm.setHotel(new Hotel());
		createHotelForm.setContact(new Contact());
		createHotelForm.setAddress(new Address());
		modelAndView.addObject("form", createHotelForm);
		return modelAndView;
	}
	
	@RequestMapping(value="/editHotel", method = RequestMethod.POST)
	public String editHotel(@ModelAttribute("form") CreateHotelForm form, BindingResult bindingResult){
		Hotel hotel = form.getHotel();
		hotel.setId(form.getId());
		hotel.setContact(form.getContact());
		hotel.setAddress(form.getAddress());
		put(BASE_URL + "/hotel/hotel", hotel);
//		return new ModelAndView("hotel_info?id=" + hotel.getId());
		return "hotel_management";
	}

	@RequestMapping(value = "/hotel_edit", method = RequestMethod.GET)
	public ModelAndView initialEditHotel(@RequestParam String id){
		ModelAndView modelAndView = new ModelAndView("hotel_edit");
		CreateHotelForm createHotelForm = new CreateHotelForm();	
		try {
			ResponseEntity<String> responseHotel= get(BASE_URL + "/hotel/hotel/" + id);

			if(responseHotel == null ){
//				result.addError(new ObjectError("Core connection", "Oops, something wrong happend, please try later"));
			} else {
				if(responseHotel.getStatusCode() == HttpStatus.OK){
					Hotel hotel = objectMapper.readValue(new JSONObject(responseHotel.getBody().toString()).toString(), Hotel.class);
					createHotelForm.setHotel(hotel);
					createHotelForm.setAddress(hotel.getAddress());
					createHotelForm.setContact(hotel.getContact());
					createHotelForm.setId(hotel.getId());
				}
			}

		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
		modelAndView.addObject("form", createHotelForm);
		return modelAndView;
	}
	
	@RequestMapping(value="/createHotel", method = RequestMethod.POST)
	public String createHotel(@ModelAttribute("form") CreateHotelForm form, BindingResult result){
		Hotel hotel = form.getHotel();
		hotel.setContact(form.getContact());
		hotel.setAddress(form.getAddress());
		ResponseEntity<String> response = post(BASE_URL + "/hotel/hotel", hotel);
		if(response == null )
			result.addError(new ObjectError("Core connection", "Oops, something wrong happend, please try later"));
		else
			if(response.getStatusCode() != HttpStatus.OK)
				result.addError(new ObjectError("Server error", (String) response.getBody()));
		if(result.hasErrors())
			return "create_hotel";
		return "hotel_management";
	}

	@RequestMapping(value="/hotel_list", method = RequestMethod.GET)
	public ModelAndView initialListHotel(){
		ModelAndView modelAndView = new ModelAndView("hotel_list");
		ResponseEntity<String> response = get(BASE_URL + "/hotel/hotels");
		if(response.getStatusCode() == HttpStatus.OK){
			JSONArray hotels = new JSONArray(response.getBody().toString());

			List<Hotel> hotelList = new ArrayList<Hotel>();
			for(int i=0;i<hotels.length();i++){
				try {
					Hotel hotel = objectMapper.readValue(hotels.getJSONObject(i).toString(), Hotel.class);
					hotelList.add(hotel);
				} catch (JSONException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			modelAndView.addObject("hotelList", hotelList);
		}
		return modelAndView;
	}

	@RequestMapping(value="/hotel_info")
	public ModelAndView hotelInfo(@RequestParam String id){
		ModelAndView modelAndView = new ModelAndView();
		ResponseEntity<String> response = get(BASE_URL + "/hotel/hotel/" + id);
		if(response.getStatusCode() == HttpStatus.OK){
			Hotel hotel;
			try {
				hotel = objectMapper.readValue(new JSONObject(response.getBody().toString()).toString(), Hotel.class);
				modelAndView.setViewName("hotel_info");				
				modelAndView.addObject("hotel", hotel);
				ResponseEntity<String> responseRooms = get(BASE_URL + "/hotel/rooms/" + id);
				List<Room> rooms = new ArrayList<Room>();
				JSONArray roomsJsonArray = new JSONArray(responseRooms.getBody().toString());
				for(int i=0;i<roomsJsonArray.length();i++){
					Room room = objectMapper.readValue(roomsJsonArray.getJSONObject(i).toString(), Room.class);
					rooms.add(room);
				}
				modelAndView.addObject("rooms", rooms);
				
				ResponseEntity<String> reservations = get(BASE_URL + "/reservation/hotel/" + id);
				JSONArray jsonArray = new JSONArray(reservations.getBody().toString());
				List<Reservation> reservationList = new ArrayList<Reservation>();
				for(int i=0;i<jsonArray.length();i++)
					reservationList.add(objectMapper.readValue(new JSONObject(jsonArray.get(i).toString()).toString(), Reservation.class));
				modelAndView.addObject("reservations", reservationList);
			} catch (JSONException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		else{
			//TODO NOT FOUND
		}
		return modelAndView;
	}

	@RequestMapping(value="/add_room", method=RequestMethod.GET)
	public ModelAndView addRoomInitial(@RequestParam String id){
		ModelAndView modelAndView = new ModelAndView("add_room");
		RoomForm roomForm = new RoomForm();
		ResponseEntity<String> response = get(BASE_URL + "/hotel/roomTypes/");
		JSONArray jsonArray = new JSONArray(response.getBody().toString());
		List<RoomType> roomTypes = new ArrayList<RoomType>();
		for(int i=0;i<jsonArray.length();i++){
			RoomType roomType;
			try {
				roomType = objectMapper.readValue(jsonArray.getJSONObject(i).toString(), RoomType.class);
				roomTypes.add(roomType);
			} catch (JSONException | IOException e) {
				e.printStackTrace();
			}

		}
		modelAndView.addObject("roomTypes", roomTypes);
		modelAndView.addObject("form", roomForm);
		modelAndView.addObject("hotelID", id);
		return modelAndView;
	}

	@RequestMapping(value="/createRoom", method=RequestMethod.POST)
	public String addRoomCreate(@ModelAttribute("form") RoomForm roomForm, @RequestParam String id, BindingResult result){
		Room room = roomForm.getRoom();
		ResponseEntity<String> responseRoomType = get(BASE_URL + "/hotel/roomType/" + roomForm.getRoomTypeID());
		RoomType roomType;
		try {
			roomType = objectMapper.readValue(new JSONObject(responseRoomType.getBody().toString()).toString(), RoomType.class);
			room.setRoomType(roomType);	
			ResponseEntity<String> responseHotel = get(BASE_URL + "/hotel/hotel/" + id);
			Hotel hotel = objectMapper.readValue(new JSONObject(responseHotel.getBody().toString()).toString(), Hotel.class);
			room.setHotel(hotel);
			ResponseEntity<String> response = post(BASE_URL + "/hotel/room", room);
			if(response.getStatusCode() == HttpStatus.OK  && !result.hasErrors())
				return "hotel_management";
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
		return "createRoom";
	}

	@RequestMapping(value="/add_room_type", method=RequestMethod.GET)
	public ModelAndView addRoomTypeInitial(){
		ModelAndView modelAndView = new ModelAndView("add_room_type");
		RoomTypeForm roomTypeForm = new RoomTypeForm();
		roomTypeForm.setRoomType(new RoomType());
		modelAndView.addObject("form", roomTypeForm);
		return modelAndView;
	}

	@RequestMapping(value="/createRoomType", method=RequestMethod.POST)
	public String addRoomTypeCreate(@ModelAttribute("form") RoomTypeForm roomTypeForm, BindingResult result){
		ResponseEntity<String> response = post(BASE_URL + "/hotel/roomType", roomTypeForm.getRoomType());
		if(response.getStatusCode() == HttpStatus.OK && !result.hasErrors())
			return "/hotel_management";
		else
			return "/niefart";
	}
	
	@RequestMapping(value="/hotelRemove", method=RequestMethod.POST)
	public String removeHotel(@QueryParam("id") String id){
		delete(BASE_URL + "/hotel/hotel/" + id);
		return "hotel_management";
	}
	
	@RequestMapping(value="/remove_reservation", method = RequestMethod.GET)
	public String removeReservation(@QueryParam("id") Long id){
		delete(BASE_URL + "/reservation/reservation/" + id);
		return "hotel_management";
	}
}
