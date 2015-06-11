package pl.edu.agh.soa.core.camel;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.apache.camel.cdi.CdiCamelContext;
import org.apache.camel.cdi.ContextName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@Startup
public class StartupCamelContext {

	@Inject
	@ContextName("camel-context")
	CdiCamelContext context;

	Logger logger = LoggerFactory.getLogger(StartupCamelContext.class);

	@PostConstruct
	public void init() {
		logger.info(">> Create CamelContext and register Camel Route.");

		try {
//			context.addRoutes(new IntegrationRoute());
			context.addRoutes(new RestConsumerRouteBuilder());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Start Camel Context
		context.start();

		logger.info(">> CamelContext created and camel route started.");
	}
	
	@PreDestroy
    public void shutdown() {
        // Graceful Shutdown Camel Context
        context.stop();
        logger.info(">> CamelContext stopped .");
    }
}
