package pl.edu.agh.soa.core.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Type;

/**
 * @author Piotr Konsek
 *
 *  class holds all accounts data with address and contact
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "ACCOUNT")
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Account implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "acc_id")
	protected Long id;

	@Column(name = "acc_password")
	@NotNull
	protected String password;

	@Column(name = "acc_first_name", length = 50)
	@NotNull
	protected String firstName;

	@Column(name = "acc_last_name", length = 50)
	@NotNull
	protected String lastName;

	@Column(name = "acc_birth_date")
	@Type(type="date")
	@NotNull
	protected Date birthDate;

	@Column(name = "acc_type", length = 1)
	@NotNull
	protected String accountType;

	@Column(name = "acc_status", length = 1)
	@NotNull
	protected String accountStatus;

	
	@JoinColumn(name = "acc_add_id", nullable=false)
	@ManyToOne(cascade = CascadeType.ALL, fetch= FetchType.EAGER)
//	@JsonBackReference
	protected Address address;

	@OneToOne(cascade = CascadeType.ALL, fetch= FetchType.EAGER)
	@JoinColumn(name = "acc_con_id", nullable=false)
//	@JsonBackReference
	protected Contact contact;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch= FetchType.EAGER)
	@JoinColumn(name="acc_hot_id")
	@NotFound(action=NotFoundAction.IGNORE)
//	@JsonBackReference
	private Hotel hotel;
	// permissions

	@Column(name = "acc_newsletter_perm")
	protected Boolean newsletterPermission;

	@Column(name = "acc_terms_accepted")
	protected Boolean termsAccepted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Boolean getNewsletterPermission() {
		return newsletterPermission;
	}

	public void setNewsletterPermission(Boolean newsletterPermission) {
		this.newsletterPermission = newsletterPermission;
	}

	public Boolean getRegulaminAccepted() {
		return termsAccepted;
	}

	public void setRegulaminAccepted(Boolean regulaminAccepted) {
		this.termsAccepted = regulaminAccepted;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}	
}
