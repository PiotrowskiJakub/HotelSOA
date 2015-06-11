package pl.edu.agh.soa.core.camel;

import org.apache.camel.builder.RouteBuilder;

public class RestConsumerRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
//		restConfiguration().component("servlet").contextPath("/core").port(8080).bindingMode(RestBindingMode.json);
		
		from("direct:mail")
        .to("smtps://smtp.gmail.com:465?username=soa.hotel@gmail.com&password=zaq1@WSXxsw2!QAZ&debugMode=true");
	}

}
