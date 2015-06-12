package pl.edu.agh.soa.ba.controller;

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

import pl.edu.agh.soa.ba.form.LoginForm;

@Controller
public class LoginController extends BaseController {

	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView loadInitialModel(){
		ModelAndView modelAndView = new ModelAndView("login");
		modelAndView.addObject("form", new LoginForm());
		return modelAndView;
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String login(@Valid @ModelAttribute("form") LoginForm form, BindingResult result){
		if(result.hasErrors())
			return "login";
		
		ResponseEntity<String> response = post(BASE_URL + "/login/in/" + form.getMail() + "/" + form.getPassword() , null);
		
		if(response == null )
			result.addError(new ObjectError("Core connection", "Oops, something wrong happend, please try later"));
		else
			if(response.getStatusCode() != HttpStatus.OK)
				result.addError(new ObjectError("Server error", (String) response.getBody()));
		if(result.hasErrors())
			return "login";
		else
			return "hotel_management";
		
	}
}
