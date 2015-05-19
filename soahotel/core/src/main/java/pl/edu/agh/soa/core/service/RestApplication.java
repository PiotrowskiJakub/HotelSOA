package pl.edu.agh.soa.core.service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import pl.edu.agh.soa.core.service.registration.implementation.AccountServiceImpl;
import pl.edu.agh.soa.core.service.test.TestRest;

@ApplicationPath("/")
public class RestApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(TestRest.class);
		classes.add(AccountServiceImpl.class);
		return classes;
	}
	
	
	@Override
	public Set<Object> getSingletons() {
		Set<Object> singletons = new HashSet<Object>();
		singletons.add(new org.codehaus.jackson.jaxrs.JacksonJsonProvider());
		return singletons;
	}
}