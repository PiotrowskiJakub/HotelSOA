package pl.edu.agh.soa.ba.form;

import java.util.Date;

/**
 * @author Piotr Konsek
 *
 */
public class RegistrationForm {
	/**
	 * first name
	 */
	private String firstName;
	/**
	 * last name
	 */
	private String lastName;
	/**
	 * birth date
	 */
	private Date birthDate;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	
}
