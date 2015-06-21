package pl.edu.agh.soa.ba.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.ws.rs.QueryParam;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.edu.agh.soa.core.bean.Reservation;

/**
 * @author Piotr Konsek
 *
 */
@Controller
public class ReservationController extends BaseController {

	@RequestMapping(value="reservationInfo", method=RequestMethod.GET)
	public ModelAndView initialReservationInfo(@QueryParam("id") Long reservationId, HttpSession session){
		ModelAndView modelAndView = new ModelAndView("reservation_info");

		ResponseEntity<String> reservationResponse = get(BASE_URL + "/reservation/reservation/" + reservationId, session);
		if(reservationResponse.getStatusCode() == HttpStatus.OK){
			try {
				Reservation reservation = objectMapper.readValue(new JSONObject(reservationResponse.getBody()).toString(), Reservation.class);
				modelAndView.addObject("reservation", reservation);
			} catch (JSONException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return modelAndView;
	}
}
