package pl.edu.agh.soa.ba.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.edu.agh.soa.core.bean.Account;
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
		Account account = new Account();
		modelAndView.addObject("account", account);
		return modelAndView;
	}	
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(@Valid @ModelAttribute("account") Account account){
		account.setAccountType(AccountType.EMPLOYEE);
		account.setBirthDate(new Date());
		account.getAddress().setPostalCode(account.getAddress().getPostalCode().replace("-", ""));
		post("http://localhost:8082/core-0.1/registration/account", account);
		return "registration";
	}
}
