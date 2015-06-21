package pl.edu.agh.soa.ba.form;


/**
 * @author Piotr Konsek
 *
 */
public class MailForm {
	private MailForm mail = new MailForm();

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

	public MailForm getMail() {
		return mail;
	}

	public void setMail(MailForm mail) {
		this.mail = mail;
	}
	
	
}
