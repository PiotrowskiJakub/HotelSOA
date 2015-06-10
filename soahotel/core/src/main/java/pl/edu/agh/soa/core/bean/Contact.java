package pl.edu.agh.soa.core.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @author Piotr Konsek
 *
 * class holds all contact data
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Contact implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="con_id")
	private Long id;
	
	@Column(name="con_phone", length=20)
	private String phone;
	
	@NotNull
	@Column(name="con_mail", length=50)
	private String mail;
	
	@Column(name="con_fax", length=50)
	@Deprecated
	private String fax;
	
	//permissions
	
	@Column(name="con_mail_perm")
	private Boolean mailPermission;
	
	@Column(name="con_sms_perm")
	private Boolean smsPermission;
	
	@Column(name="con_fax_perm")
	@Deprecated
	private Boolean faxPermission;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public Boolean getMailPermission() {
		return mailPermission;
	}
	public void setMailPermission(Boolean mailPermission) {
		this.mailPermission = mailPermission;
	}
	public Boolean getSmsPermission() {
		return smsPermission;
	}
	public void setSmsPermission(Boolean smsPermission) {
		this.smsPermission = smsPermission;
	}
	public Boolean getFaxPermission() {
		return faxPermission;
	}
	public void setFaxPermission(Boolean faxPermission) {
		this.faxPermission = faxPermission;
	}
}
