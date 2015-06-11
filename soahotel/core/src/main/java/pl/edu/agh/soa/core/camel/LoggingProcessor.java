package pl.edu.agh.soa.core.camel;

import java.math.BigDecimal;

import javax.ws.rs.core.Response;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.bean.BeanInvocation;

import pl.edu.agh.soa.core.bean.AdditionalServiceType;

public class LoggingProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		BeanInvocation beanInvocation = exchange.getIn().getBody(
				BeanInvocation.class);

		/**
		 * Get the invoked REST service method name and build a response to send
		 * back to the client.
		 */
		String methodName = beanInvocation.getMethod().getName();

		if (methodName.equals("getCustomers")) {
			AdditionalServiceType ast = new AdditionalServiceType(new Long(1), "Nazwa", "Opis", new BigDecimal(12.3));
			exchange.getOut().setBody(Response.ok(ast).build());
		} else {
			/**
			 * No customer exists for the provided id, so return HTTP 404 - Not
			 * Found.
			 */
			exchange.getOut().setBody(
					Response.status(Response.Status.NOT_FOUND).build());
		}
	}

}
