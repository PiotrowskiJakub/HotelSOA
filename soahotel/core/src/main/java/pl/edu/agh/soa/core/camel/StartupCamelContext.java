package pl.edu.agh.soa.core.camel;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

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

		configureSslCertificates();
		try {
			// context.addRoutes(new IntegrationRoute());
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

	private void configureSslCertificates() {
		SSLContext ctx = null;
		TrustManager[] trustAllCerts = new X509TrustManager[] { new X509TrustManager() {
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(X509Certificate[] certs,
					String authType) {
			}

			public void checkServerTrusted(X509Certificate[] certs,
					String authType) {
			}
		} };
		try {
			ctx = SSLContext.getInstance("SSL");
			ctx.init(null, trustAllCerts, null);
		} catch (NoSuchAlgorithmException | KeyManagementException e) {
		}

		SSLContext.setDefault(ctx);
	}
}
