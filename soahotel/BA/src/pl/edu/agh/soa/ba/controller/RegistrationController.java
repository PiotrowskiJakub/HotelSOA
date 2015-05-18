package pl.edu.agh.soa.ba.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import pl.edu.agh.soa.core.Account;



@Controller
public class RegistrationController {

	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView loadInitialModel(){
		ModelAndView modelAndView = new ModelAndView("registration");
		Account account = new Account();
		modelAndView.addObject("account", account);
		return modelAndView;
	}	
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(@Valid @ModelAttribute("account") Account account){
		System.out.println("dupa");
		new RestTemplate().put("http://localhost:8082/core/rest/test", account);
		return "registration";
	}
}
