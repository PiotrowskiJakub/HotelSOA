package pl.edu.agh.soa.ba.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.edu.agh.soa.ba.form.ReportForm;
import pl.edu.agh.soa.core.bean.Hotel;

@Controller
public class ReportController extends BaseController {

	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public ModelAndView loadInitialModel(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("report");
		ReportForm reportForm = new ReportForm();
		// Get all hotels
		ResponseEntity<String> response = get(BASE_URL + "/hotel/hotels/", session);
		List<Hotel> hotels = new ArrayList<Hotel>();
		if(response.getStatusCode() == HttpStatus.OK) {
			JSONArray jsonArray = new JSONArray(response.getBody().toString());
			
			for (int i = 0; i < jsonArray.length(); i++) {
				Hotel hotel;
				try {
					hotel = objectMapper.readValue(jsonArray.getJSONObject(i)
							.toString(), Hotel.class);
					hotels.add(hotel);
				} catch (JSONException | IOException e) {
					e.printStackTrace();
				}

			}
		}

		modelAndView.addObject("hotels", hotels);
		modelAndView.addObject("form", reportForm);
		return modelAndView;
	}

	@RequestMapping(value = "/generateReport", method = RequestMethod.POST)
	public String generateReport(@ModelAttribute("form") ReportForm reportForm,
			HttpServletResponse response, HttpSession session) throws IOException {

		ResponseEntity<byte[]> responseReport = getFile(BASE_URL + "/report/" + reportForm.getHotelID(), session);
		if(responseReport.getStatusCode() == HttpStatus.OK && responseReport.getBody() != null) {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition",
					"attachment;filename=Raport_rezerwacji.pdf");

			ServletOutputStream out = response.getOutputStream();

			
			out.write(responseReport.getBody());
			out.flush();
			out.close();
			return null;
		}
		
		return "report";
	}
}
