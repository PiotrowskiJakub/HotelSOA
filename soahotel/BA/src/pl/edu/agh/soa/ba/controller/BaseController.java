package pl.edu.agh.soa.ba.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

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
	ObjectMapper objectMapper;

	public static final String BASE_URL = "http://localhost:8080/core";
//	public static final String BASE_URL = "http://soahotelcore-hotelcore.rhcloud.com/core-0.1";
	
	public static final String TOKEN = "TOKEN";
	public static final String ACCOUNT = "ACCOUNT";


	public BaseController() {
		objectMapper = new ObjectMapper();
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
	 * @return 
	 */
	protected void put(String url, Object request) {		
		try{
			 restTemplate.put(url, request);
		} catch (HttpClientErrorException e){
			e.printStackTrace();
		}	
	}
	
	protected ResponseEntity<String> put(String url, Object object, HttpSession session) {		
		ResponseEntity<String> response = null;
		try{
			HttpEntity<Object> request = new HttpEntity<Object>(object, getHeadersWithAuth(session.getAttribute(TOKEN).toString()));
			response = restTemplate.exchange(url, HttpMethod.PUT, request, String.class);
		} catch (HttpClientErrorException e){
			e.printStackTrace();
			session.removeAttribute(TOKEN);
		}	
		return response;
	}

	/**
	 *  post for entity
	 * @param url
	 * @param request
	 * @return
	 */
	protected ResponseEntity<String> post(String url, Object request) {
		ResponseEntity<String> response = null;
		try{
			response = restTemplate.postForEntity(url, request, String.class);
		} catch (HttpClientErrorException e){
			e.printStackTrace();
		}	
		return response;
	}
	
	protected ResponseEntity<String> post(String url, Object object, HttpSession session) {
		ResponseEntity<String> response = null;
		try{
			HttpEntity<Object> request = new HttpEntity<Object>(object, getHeadersWithAuth(session.getAttribute(TOKEN).toString()));
			response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
		} catch (HttpClientErrorException e){
			e.printStackTrace();
			session.removeAttribute(TOKEN);
		}	
		return response;
	}

	protected ResponseEntity<String> get(String url) {
		ResponseEntity<String> response = null;
		try{
			response = restTemplate.getForEntity(url, String.class);
		} catch (HttpClientErrorException e){
			e.printStackTrace();
		}	
		return response;
	}
	
	protected ResponseEntity<String> get(String url, HttpSession session) {
		ResponseEntity<String> response = null;
		try{
			HttpEntity<Object> request = new HttpEntity<Object>(getHeadersWithAuth(session.getAttribute(TOKEN).toString()));
			response = restTemplate.exchange(url,  HttpMethod.GET, request, String.class);
		} catch (HttpClientErrorException e){
			e.printStackTrace();
			session.removeAttribute(TOKEN);
		}	
		return response;
	}

	protected void delete(String url) {
		restTemplate.delete(url);
	}
	
	protected ResponseEntity<String> delete(String url, HttpSession session) {
		ResponseEntity<String> response = null;
		try{
			HttpEntity<Object> request = new HttpEntity<Object>(getHeadersWithAuth(session.getAttribute(TOKEN).toString()));
			response = restTemplate.exchange(url,  HttpMethod.DELETE, request, String.class);
		} catch (HttpClientErrorException e){
			e.printStackTrace();
			session.removeAttribute(TOKEN);
		}	
		return response;
	}
	
	protected ResponseEntity<byte[]> getFile(String url) {
		ResponseEntity<byte[]> response = null;
		try{
			response = restTemplate.getForEntity(url, byte[].class);
		} catch (HttpClientErrorException e){
			e.printStackTrace();
		}	
		return response;
	}
	
	protected ResponseEntity<byte[]> getFile(String url, HttpSession session) {
		ResponseEntity<byte[]> response = null;
		try{
			HttpEntity<Object> request = new HttpEntity<Object>(getHeadersWithAuth(session.getAttribute(TOKEN).toString()));
			response = restTemplate.exchange(url,  HttpMethod.GET, request, byte[].class);
		} catch (HttpClientErrorException e){
			e.printStackTrace();
			session.removeAttribute(TOKEN);
		}	
		return response;
	}
	
	private HttpHeaders getHeadersWithAuth(String token){
		HttpHeaders headers = new HttpHeaders();
		headers.set("Token-Auth", token);
		return headers;
	}
}
