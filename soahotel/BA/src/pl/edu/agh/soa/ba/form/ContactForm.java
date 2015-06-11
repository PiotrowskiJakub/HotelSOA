package pl.edu.agh.soa.ba.form;

import pl.edu.agh.soa.core.bean.Contact;

public class ContactForm {
	private Contact contact;

	public String getFax() {
		return contact.getFax();
	}

	public Boolean getFaxPermission() {
		return contact.getFaxPermission();
	}

	public String getMail() {
		return contact.getMail();
	}

	public Boolean getMailPermission() {
		return contact.getMailPermission();
	}

	public String getPhone() {
		return contact.getPhone();
	}

	public Boolean getSmsPermission() {
		return contact.getSmsPermission();
	}

	public void setFax(String fax) {
		contact.setFax(fax);
	}

	public void setFaxPermission(Boolean faxPermission) {
		contact.setFaxPermission(faxPermission);
	}

	public void setMail(String mail) {
		contact.setMail(mail);
	}

	public void setMailPermission(Boolean mailPermission) {
		contact.setMailPermission(mailPermission);
	}

	public void setPhone(String phone) {
		contact.setPhone(phone);
	}

	public void setSmsPermission(Boolean smsPermission) {
		contact.setSmsPermission(smsPermission);
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	
}
