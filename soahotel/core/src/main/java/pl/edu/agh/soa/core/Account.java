package pl.edu.agh.soa.core;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Piotr Konsek
 *
 * class holds all accounts data with address and contact
 */
@SuppressWarnings("serial")
@Entity
@Table(name="ACCOUNT")
public class Account implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator="account_id_seq")
    @SequenceGenerator(name="account_id_seq", sequenceName="account_id_seq", allocationSize=1)
	@Column(name="ACCOUNT_ID")
	private Long id;
	
	private String firstName;
	private String lastName;
	private Date birthDate;
	private String accountType;
	private String accountStatus;
	
//	@Embedded
//	private Address address;
//	
//	@Embedded
//	private Contact contact;
	
	//permissions
	private Boolean newsletterPermission;
	private Boolean regulaminAccepted;
	
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
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
//	public Address getAddress() {
//		return address;
//	}
//	public void setAddress(Address address) {
//		this.address = address;
//	}
//	public Contact getContact() {
//		return contact;
//	}
//	public void setContact(Contact contact) {
//		this.contact = contact;
//	}
	public Boolean getNewsletterPermission() {
		return newsletterPermission;
	}
	public void setNewsletterPermission(Boolean newsletterPermission) {
		this.newsletterPermission = newsletterPermission;
	}
	public Boolean getRegulaminAccepted() {
		return regulaminAccepted;
	}
	public void setRegulaminAccepted(Boolean regulaminAccepted) {
		this.regulaminAccepted = regulaminAccepted;
	}
	
	
}
