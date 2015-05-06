package pl.edu.agh.soa.core;

/**
 * @author piotr
 *
 * class holds all contact data
 */
public class Contact {
	private String phone;
	private String mail;
	private String fax;
	
	//permissions
	private Boolean mailPermission;
	private Boolean smsPermission;
	private Boolean faxPermission;
	
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
