package pl.edu.agh.soa.ba.form;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Piotr Konsek
 *
 */
public class LoginForm {
	@NotEmpty(message = "Mail can't be empty!")
	private String mail;
	
	@NotEmpty(message = "Password can't be empty!")
	private String password;
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
