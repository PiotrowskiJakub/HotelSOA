package pl.edu.agh.soa.ba.controller;

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
	
}
