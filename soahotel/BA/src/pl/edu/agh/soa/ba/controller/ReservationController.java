package pl.edu.agh.soa.ba.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.edu.agh.soa.ba.form.MailForm;
import pl.edu.agh.soa.core.bean.Complaint;
import pl.edu.agh.soa.core.bean.Reservation;

/**
 * @author Piotr Konsek
 *
 */
@Controller
public class ReservationController extends BaseController {

	@RequestMapping(value="reservationInfo", method=RequestMethod.GET)
	public ModelAndView initialReservationInfo(@RequestParam("id") String reservationId, HttpSession session){
		ModelAndView modelAndView = new ModelAndView("reservation_info");
		MailForm mailForm = new MailForm();

		ResponseEntity<String> reservationResponse = get(BASE_URL + "/reservation/reservation/" + reservationId, session);
		if(reservationResponse.getStatusCode() == HttpStatus.OK){
			try {
				Reservation reservation = objectMapper.readValue(new JSONObject(reservationResponse.getBody()).toString(), Reservation.class);
				modelAndView.addObject("reservation", reservation);
				ResponseEntity<String> complaintResponse = get(BASE_URL + "/complaint/reservation/" + reservationId, session);
				if(complaintResponse.getStatusCode() == HttpStatus.OK){
					Complaint complaint = objectMapper.readValue(new JSONObject(complaintResponse.getBody().toString()).toString(), Complaint.class);
					modelAndView.addObject("complaint", complaint);
				}
					
				mailForm.setEmail(reservation.getAccount().getContact().getMail());
				mailForm.setSubject("Complaint response");
			} catch (JSONException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		modelAndView.addObject("form", mailForm);
		return modelAndView;
	}
	
	@RequestMapping(value="/sendMessage", method=RequestMethod.POST)
	public String sendMessage(@ModelAttribute("form") MailForm form, BindingResult result, HttpSession session){
		@SuppressWarnings("unused")
		ResponseEntity<String> reservationResponse = post(BASE_URL + "/complaint/mail", form.getMail() , session);		
		return "hotel_management";
	}
}
