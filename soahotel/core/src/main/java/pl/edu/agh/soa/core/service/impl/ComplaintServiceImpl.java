package pl.edu.agh.soa.core.service.impl;

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

import pl.edu.agh.soa.core.bean.Account;
import pl.edu.agh.soa.core.bean.Complaint;
import pl.edu.agh.soa.core.dao.ComplaintDAO;
import pl.edu.agh.soa.core.dict.EmailMessages;
import pl.edu.agh.soa.core.dto.Mail;
import pl.edu.agh.soa.core.service.ComplaintService;

/**
 * Created by agnieszkaszczurek on 20.06.15.
 */
@Stateless
public class ComplaintServiceImpl implements ComplaintService {

	@EJB
	ComplaintDAO complaintDAO;

	@Override
	public void createComplaint(Complaint complaint) {
		complaintDAO.addComplaint(complaint);
		sendMial(complaint);
	}

	@Override
	public Complaint getComplaint(Long id) {
		return complaintDAO.getComplaintById(id);
	}

	@Override
	public void updateComplaint(Complaint complaint) {
		complaintDAO.updateComplaint(complaint);
	}

	@Override
	public void deleteComplaint(Long id) {
		complaintDAO.removeComplaint(id);
	}

	@Override
	public List<Complaint> getAllComplaints() {
		return complaintDAO.getAllComplaints();
	}

	@Override
	public Complaint getComplaintByReservationId(Long id) {
		return complaintDAO.getComplaintByReservaitonId(id);
	}

	@Override
	public void deleteComplaintByReservationId(Long id) {
		complaintDAO.deleteComplaintByReservationId(id);
	}

	@Override
	public void sendMailToUser(Mail mail) {
		sendMial(mail);
	}

	private void sendMial(Mail mail) {
		Session session = configureSession();
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("soa.hotel@gmail.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					mail.getEmail()));
			message.setSubject(mail.getSubject());
			message.setText(mail.getMessage());

			Transport.send(message);
		} catch (MessagingException e) {
			// throw new RuntimeException(e);
		}
	}

	private void sendMial(Complaint complaint) {
		Account account = complaint.getReservation().getAccount();

		Session session = configureSession();
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("soa.hotel@gmail.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					account.getContact().getMail()));
			message.setSubject(EmailMessages.COMPLAITN_SUBJECT);
			message.setText(account.getFirstName()
					+ EmailMessages.COMPLAINT_MESSAGE
					+ complaint.getDescription() + "\""
					+ EmailMessages.END_MESSAGE);

			Transport.send(message);

			message = new MimeMessage(session);
			message.setFrom(new InternetAddress("soa.hotel@gmail.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					"soa.hotel@gmail.com"));
			message.setSubject(EmailMessages.COMPLAITN_EMPLOYEE_SUBJECT);
			message.setText("Użytkownik " + account.getFirstName() + " "
					+ account.getLastName()
					+ EmailMessages.COMPLAINT_EMPLOYEE_MESSAGE
					+ complaint.getDescription() + "\""
					+ EmailMessages.END_MESSAGE);

			Transport.send(message);
		} catch (MessagingException e) {
			// throw new RuntimeException(e);
		}
	}

	private Session configureSession() {
		configureSslCertificates();
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		return Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("soa.hotel@gmail.com",
						"zaq1@WSXxsw2!QAZ");
			}
		});
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
