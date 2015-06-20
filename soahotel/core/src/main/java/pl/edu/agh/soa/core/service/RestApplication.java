package pl.edu.agh.soa.core.service;

import pl.edu.agh.soa.core.service.rest.*;
import pl.edu.agh.soa.core.service.test.TestRest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class RestApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(TestRest.class);
		classes.add(RegisterWS.class);
		classes.add(AstRest.class);
		classes.add(LoginWS.class);
		classes.add(ReservationWS.class);
		classes.add(HotelWS.class);
		classes.add(ComplaintWS.class);
		return classes;
	}
	
	
	@Override
	public Set<Object> getSingletons() {
		Set<Object> singletons = new HashSet<Object>();
		singletons.add(new org.codehaus.jackson.jaxrs.JacksonJsonProvider());
		return singletons;
	}
}
