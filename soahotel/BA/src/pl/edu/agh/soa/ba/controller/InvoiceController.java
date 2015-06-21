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

import pl.edu.agh.soa.ba.form.InvoiceForm;
import pl.edu.agh.soa.core.bean.Reservation;

@Controller
public class InvoiceController extends BaseController {

	@RequestMapping(value = "/invoice", method = RequestMethod.GET)
	public ModelAndView loadInitialModel(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("invoice");
		InvoiceForm invoiceForm = new InvoiceForm();
		// Get all hotels
		ResponseEntity<String> response = get(BASE_URL + "/reservation/list/", session);
		List<Reservation> reservations = new ArrayList<Reservation>();
		if(response.getStatusCode() == HttpStatus.OK) {
			JSONArray jsonArray = new JSONArray(response.getBody().toString());
			
			for (int i = 0; i < jsonArray.length(); i++) {
				Reservation reservation;
				try {
					reservation = objectMapper.readValue(jsonArray.getJSONObject(i).toString()
							, Reservation.class);
					reservations.add(reservation);
				} catch (JSONException | IOException e) {
					e.printStackTrace();
				}

			}
		}

		modelAndView.addObject("reservations", reservations);
		modelAndView.addObject("form", invoiceForm);
		return modelAndView;
	}

	@RequestMapping(value = "/generateInvoice", method = RequestMethod.POST)
	public String generateReport(@ModelAttribute("form") InvoiceForm invoiceForm,
			HttpServletResponse response, HttpSession session) throws IOException {

		ResponseEntity<byte[]> responseInvoice = postFile(BASE_URL + "/invoices/" + invoiceForm.getReservationID(), null, session);
		if(responseInvoice.getStatusCode() == HttpStatus.OK && responseInvoice.getBody() != null) {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition",
					"attachment;filename=Faktura.pdf");

			ServletOutputStream out = response.getOutputStream();

			
			out.write(responseInvoice.getBody());
			out.flush();
			out.close();
			return null;
		}
		
		return "invoice";
	}
}
