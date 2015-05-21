package pl.edu.agh.soa.core.bean;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * @author Piotr Konsek
 *
 */
@Entity
@AttributeOverrides({
	@AttributeOverride(name="id", column=@Column(name="emp_id")),
	@AttributeOverride(name="firstName", column=@Column(name="emp_first_name")),
	@AttributeOverride(name="lastName", column=@Column(name="emp_last_name")),
	@AttributeOverride(name="birthDate", column=@Column(name="emp_birth_date")),
	@AttributeOverride(name="accountType", column=@Column(name="emp_type")),
	@AttributeOverride(name="accountStatus", column=@Column(name="emp_status")),
//	@AttributeOverride(name="acc_id_", column=@Column(name="emp_address")),
//	@AttributeOverride(name="contact", column=@Column(name="emp_contact")),
	@AttributeOverride(name="newsletterPermission", column=@Column(name="emp_newsletter_perm")),
	@AttributeOverride(name="termsAccepted", column=@Column(name="emp_term_accepted"))
	
})
public class Employee extends Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="emp_hot_id")
	private Hotel hotel;
	
	//I can't inherit those objects.. and I don't figure out why
	// Resolved - Jakub Piotrowski ;)
	@OneToOne
	@JoinColumn(name = "acc_add_id")
	protected Address address;

	@OneToOne
	@JoinColumn(name = "acc_con_id")
	protected Contact contact;

}
