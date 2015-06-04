package pl.edu.agh.soa.ba.controller;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Piotr Konsek
 * 
 * Contains method to handle http request.
 * Manages rest connection 
 *
 */
@Controller
public abstract class BaseController {
	private RestTemplate restTemplate;
	
	public BaseController() {
		restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView initialRequest(){
		return new ModelAndView("index");
	}
	
	/**
	 * put object to given url
	 * @param url
	 * @param request
	 */
	protected void put(String url, Object request) {		
		try{
			restTemplate.put(url, request);
		} catch (HttpClientErrorException e){
			e.printStackTrace();
		}	
	}
	
	protected void post(String url, Object request) {
		try{
			restTemplate.postForLocation(url, request);
		} catch (HttpClientErrorException e){
			e.printStackTrace();
		}	
	}

}
