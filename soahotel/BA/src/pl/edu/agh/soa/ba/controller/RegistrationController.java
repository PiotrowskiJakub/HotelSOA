package pl.edu.agh.soa.ba.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.edu.agh.soa.ba.form.AccountForm;
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
		modelAndView.addObject("form", new AccountForm());
		return modelAndView;
	}	
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(@Valid @ModelAttribute("form") AccountForm registrationForm, BindingResult result){
		registrationForm.setAccountType(AccountType.EMPLOYEE);
		registrationForm.setBirthDate(new Date());
		registrationForm.getAddress().setPostalCode(registrationForm.getAddress().getPostalCode().replace("-", ""));
		ResponseEntity<String> response = post(BASE_URL + "/registration/account", registrationForm.getAccount());
		if(response == null )
			result.addError(new ObjectError("Core connection", "Oops, something wrong happend, please try later"));
		else
			if(response.getStatusCode() != HttpStatus.OK)
				result.addError(new ObjectError("Server error", (String) response.getBody()));
		if(result.hasErrors())
			return "registration";
		else
			return "login";
		
	}
}
