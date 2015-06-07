package pl.edu.agh.soa.ba.controller;

import java.io.IOException;

import javax.xml.ws.BindingProvider;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

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

	public static final String BASE_URL = "http://localhost:8082/core-0.1";

	public BaseController() {
		restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
				restTemplate.setErrorHandler(new DefaultResponseErrorHandler(){
					@Override
					protected boolean hasError(HttpStatus statusCode) {
						return false;
					}
		
					@Override
					public boolean hasError(ClientHttpResponse response)
							throws IOException {
						return false;
					}
					
				});
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
//				String responseString = e.getResponseBodyAsString();
//				ObjectMapper mapper = new ObjectMapper();
//				String result = mapper.readValue(responseString, String.class);
		}	
	}

	protected ResponseEntity<String> post(String url, Object request) {
		ResponseEntity<String> response = null;
		try{
			response = restTemplate.postForEntity(url, request, String.class);
		} catch (HttpClientErrorException e){
			e.printStackTrace();
		}	
		return response;
	}

}
