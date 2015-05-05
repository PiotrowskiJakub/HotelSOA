package pl.edu.agh.soa.ba.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.edu.agh.soa.ba.form.RegistrationForm;

@Controller
public class RegistrationController {

	
	@RequestMapping(value="/registration", method = RequestMethod.POST)
	public ModelAndView loadInitialModel(){
		ModelAndView modelAndView = new ModelAndView("registration");
		RegistrationForm registrationForm = new RegistrationForm();
		modelAndView.addObject("form", registrationForm);
		return modelAndView;
	}	
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String create(@Valid @ModelAttribute("form") RegistrationForm registrationForm){
		
		return "registration";
	}
}
