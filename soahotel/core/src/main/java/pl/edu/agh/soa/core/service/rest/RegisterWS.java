package pl.edu.agh.soa.core.service.rest;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pl.edu.agh.soa.core.bean.Account;
import pl.edu.agh.soa.core.bean.Token;
import pl.edu.agh.soa.core.dict.AccountStatus;
import pl.edu.agh.soa.core.dict.EmailMessages;
import pl.edu.agh.soa.core.service.LoginService;
import pl.edu.agh.soa.core.service.RegistrationService;

@Stateless
@Path("registration")
public class RegisterWS {

	@EJB
	RegistrationService registrationService;
	
	@EJB
	LoginService loginService;

	// @Override
	@POST
	@Path("account")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addAccount(Account account) {
		account = registrationService.addAccount(account);

		sendMial(account);
		return Response.ok().build();
	}

	// @Override
	public void updateAccount(Account account) {
		// TODO Auto-generated method stub

	}

	// @Override
	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAccount() {
		List<Account> accList = registrationService.getAccountList();
		return Response.ok(accList).build();
	}

	// @Override
	public Account getAccount(Integer id) {
		// TODO Auto-generated method stu
		return null;
	}

	// @Override
	public void removeAccount(Integer id) {
		// TODO Auto-generated method stub

	}

	// @Override
	public String test() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@GET
	@Path("confirm/{accountId}/{token}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response confirmAccount(@PathParam("accountId") Long accountId, @PathParam("token") String token) {
		if(registrationService.checkToken(token)) {
			Account account = registrationService.getAccount(accountId);
			account.setAccountStatus(AccountStatus.ACTIVE);
			registrationService.updateAccount(account);
			return Response.ok("Twoje konto zostalo aktywowane.").build();
		}

		return Response.ok("Link nieaktywny").build();
	}

	private void sendMial(Account account) {
		configureSslCertificates();
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								"soa.hotel@gmail.com", "zaq1@WSXxsw2!QAZ");
					}
				});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("soa.hotel@gmail.com"));
			message.addRecipient(Message.RecipientType.TO,
					new InternetAddress(account.getContact().getMail()));
			message.setSubject(EmailMessages.SUBJECT);
			Token token = loginService.createToken();
			message.setText(account.getFirstName()
					+ EmailMessages.MESSAGE + account.getId() + "/" + token.getToken() + EmailMessages.END_MESSAGE);

			Transport.send(message);
		} catch (MessagingException e) {
			// throw new RuntimeException(e);
		}
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
