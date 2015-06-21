package pl.edu.agh.soa.ba.form;

import pl.edu.agh.soa.core.dto.Mail;

/**
 * @author Piotr Konsek
 *
 */
public class MailForm {
	private Mail mail = new Mail();

	public String getEmail() {
		return mail.getEmail();
	}

	public String getMessage() {
		return mail.getMessage();
	}

	public String getSubject() {
		return mail.getSubject();
	}

	public void setEmail(String email) {
		mail.setEmail(email);
	}

	public void setMessage(String message) {
		mail.setMessage(message);
	}

	public void setSubject(String subject) {
		mail.setSubject(subject);
	}

	public Mail getMail() {
		return mail;
	}

	public void setMail(Mail mail) {
		this.mail = mail;
	}
	
	
}
