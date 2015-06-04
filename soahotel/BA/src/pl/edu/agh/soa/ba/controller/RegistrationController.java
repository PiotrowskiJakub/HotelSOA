package pl.edu.agh.soa.ba.controller;

import java.util.Date;

import javax.validation.Valid;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.edu.agh.soa.ba.form.RegistrationForm;
import pl.edu.agh.soa.core.dict.AccountType;

/**
 * @author Piotr Konsek
 * registration controller
 */
@Controller
public class RegistrationController extends BaseController {
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView loadInitialModel(){
		ModelAndView modelAndView = new ModelAndView("registration");
		modelAndView.addObject("form", new RegistrationForm());
		return modelAndView;
	}	
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(@Valid @ModelAttribute("form") RegistrationForm registrationForm, BindingResult result){
		registrationForm.setAccountType(AccountType.EMPLOYEE);
		registrationForm.setBirthDate(new Date());
		registrationForm.getAddress().setPostalCode(registrationForm.getAddress().getPostalCode().replace("-", ""));
		Response response = post("http://localhost:8082/core-0.1/registration/account", registrationForm.getAccount());
		if(response == null )
			result.addError(new ObjectError("Core connection", "Oops, something wrong happend, please try later"));
		else
			if(response.getStatus() != 200)
				result.addError(new ObjectError("Server error", (String) response.getEntity()));
		if(result.hasErrors())
			return "registration";
		else
			return "login";
		
	}
}
