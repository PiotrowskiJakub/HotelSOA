package pl.edu.agh.soa.core;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestRest {
	
	AccountData user;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody String getUser(){
		user = new AccountData();
	
//		user.setAge(22);
		return "This is rest test ;)";
//		return user;
	}
	
	
}
