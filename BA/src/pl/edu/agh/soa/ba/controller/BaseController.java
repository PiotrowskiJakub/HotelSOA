package pl.edu.agh.soa.ba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BaseController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView initialRequest(){
		return new ModelAndView("index");
	}
}
