package pl.edu.agh.soa.core.camel;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

public class IntegrationRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
//		from("timer://outputCustomers?period=30000")
//        .log("Updating customers.json")
//        .to("restlet://http://localhost:8080/core/ast")
//        .setHeader(Exchange.FILE_NAME, constant("ast.json"))
//        .to("file:{{jboss.server.data.dir}}/ast-records/");
		
		from("timer://outputCustomers?period=5000")
		.log("Send to direct")
		.to("restlet://http://localhost:8080/core/ast")
        .setHeader(Exchange.FILE_NAME, constant("ast.json"))
        .to("direct:ast");
	}

}
