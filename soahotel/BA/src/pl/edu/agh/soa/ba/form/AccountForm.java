package pl.edu.agh.soa.ba.form;

import java.util.Date;

import javax.validation.constraints.AssertTrue;

import org.hibernate.validator.constraints.NotEmpty;

import pl.edu.agh.soa.core.bean.Account;
import pl.edu.agh.soa.core.bean.Address;
import pl.edu.agh.soa.core.bean.Contact;

/**
 * Wrapper for bean from core
 * @author Piotr Konsek
 *
 */
public class AccountForm {
	private Account account;
	
	@NotEmpty(message = "Confirmation password can't be empty!")
	private String confirmationPassword;
	
	public String getConfirmationPassword() {
		return confirmationPassword;
	}

	public void setConfirmationPassword(String confirmationPassword) {
		this.confirmationPassword = confirmationPassword;
	}

	public AccountForm(){
		account = new Account();
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getAccountStatus() {
		return account.getAccountStatus();
	}

	public String getAccountType() {
		return account.getAccountType();
	}

	public Address getAddress() {
		return account.getAddress();
	}
	
//TODO
	public Date getBirthDate() {
		return account.getBirthDate();
	}

	public Contact getContact() {
		return account.getContact();
	}

	@NotEmpty(message="First name can't be empty!")
	public String getFirstName() {
		return account.getFirstName();
	}

	@NotEmpty(message = "Last name can't be empty")
	public String getLastName() {
		return account.getLastName();
	}

	public Boolean getNewsletterPermission() {
		return account.getNewsletterPermission();
	}

	@NotEmpty(message = "Password can't be empty")
	public String getPassword() {
		return account.getPassword();
	}

	@AssertTrue(message = "Regulamin has to be accepted!")
	public Boolean getRegulaminAccepted() {
		return account.getRegulaminAccepted();
	}
	
	@NotEmpty(message = "City can't be empty")
	public String getCity() {
		return getAddress().getCity();
	}

	@NotEmpty(message = "Country can't be empty")
	public String getCountry() {
		return getAddress().getCountry();
	}

	public Integer getFlatNumber() {
		return getAddress().getFlatNumber();
	}

	public String getHouseNumber() {
		return getAddress().getHouseNumber();
	}

	public Double getLatitude() {
		return getAddress().getLatitude();
	}

	public Double getLongitude() {
		return getAddress().getLongitude();
	}

	public String getPostalCode() {
		return getAddress().getPostalCode();
	}

	public String getState() {
		return getAddress().getState();
	}

	public String getStreet() {
		return getAddress().getStreet();
	}

	public void setAccountStatus(String accountStatus) {
		account.setAccountStatus(accountStatus);
	}

	public void setAccountType(String accountType) {
		account.setAccountType(accountType);
	}

	public void setAddress(Address address) {
		account.setAddress(address);
	}

	public void setBirthDate(Date birthDate) {
		account.setBirthDate(birthDate);
	}

	public void setContact(Contact contact) {
		account.setContact(contact);
	}

	public void setFirstName(String firstName) {
		account.setFirstName(firstName);
	}

	public void setLastName(String lastName) {
		account.setLastName(lastName);
	}

	public void setNewsletterPermission(Boolean newsletterPermission) {
		account.setNewsletterPermission(newsletterPermission);
	}

	public void setPassword(String password) {
		account.setPassword(password);
	}

	public void setRegulaminAccepted(Boolean regulaminAccepted) {
		account.setRegulaminAccepted(regulaminAccepted);
	}

	public void setCity(String city) {
		getAddress().setCity(city);
	}

	public void setCountry(String country) {
		getAddress().setCountry(country);
	}

	public void setFlatNumber(Integer flatNumber) {
		getAddress().setFlatNumber(flatNumber);
	}

	public void setHouseNumber(String houseNumber) {
		getAddress().setHouseNumber(houseNumber);
	}

	public void setLatitude(Double latitude) {
		getAddress().setLatitude(latitude);
	}

	public void setLongitude(Double longitude) {
		getAddress().setLongitude(longitude);
	}

	public void setPostalCode(String postalCode) {
		getAddress().setPostalCode(postalCode);
	}

	public void setState(String state) {
		getAddress().setState(state);
	}

	public void setStreet(String street) {
		getAddress().setStreet(street);
	}

	public String getFax() {
		return getContact().getFax();
	}

	public Boolean getFaxPermission() {
		return getContact().getFaxPermission();
	}

	public Long getId() {
		return getContact().getId();
	}

	public String getMail() {
		return getContact().getMail();
	}

	public Boolean getMailPermission() {
		return getContact().getMailPermission();
	}

	public String getPhone() {
		return getContact().getPhone();
	}

	public Boolean getSmsPermission() {
		return getContact().getSmsPermission();
	}

	public void setFax(String fax) {
		getContact().setFax(fax);
	}

	public void setFaxPermission(Boolean faxPermission) {
		getContact().setFaxPermission(faxPermission);
	}

	public void setId(Long id) {
		getContact().setId(id);
	}

	public void setMail(String mail) {
		getContact().setMail(mail);
	}

	public void setMailPermission(Boolean mailPermission) {
		getContact().setMailPermission(mailPermission);
	}

	public void setPhone(String phone) {
		getContact().setPhone(phone);
	}

	public void setSmsPermission(Boolean smsPermission) {
		getContact().setSmsPermission(smsPermission);
	}

	
	
}
