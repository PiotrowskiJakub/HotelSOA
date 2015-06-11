package pl.edu.agh.soa.ba.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.QueryParam;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.edu.agh.soa.ba.form.CreateHotelForm;
import pl.edu.agh.soa.core.bean.Address;
import pl.edu.agh.soa.core.bean.Contact;
import pl.edu.agh.soa.core.bean.Hotel;

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
		return "succes";
	}
	
	@RequestMapping(value="/hotel_list", method = RequestMethod.GET)
	public ModelAndView initialListHotel(){
		ModelAndView modelAndView = new ModelAndView("hotel_list");
		ResponseEntity<String> response = get(BASE_URL + "/hotel/hotels");
		if(response.getStatusCode() == HttpStatus.OK){
			JSONArray hotels = new JSONArray(response.getBody().toString());
			ObjectMapper objectMapper = new ObjectMapper();
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

	@RequestMapping(value="/edit_hotel")
	public ModelAndView editHotel(@QueryParam("hotel") Hotel hotel){
		ModelAndView modelAndView = new ModelAndView();
		
		return modelAndView;
	}
	
}
