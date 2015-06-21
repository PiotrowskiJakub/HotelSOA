package pl.edu.agh.soa.core.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * @author Piotr Konsek
 *
 */
@Entity
public class Invoice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3395170748298136609L;

	@Id
	@GeneratedValue
	@Column(name = "inv_id")
	private Long id;
	
	@Column(name = "inv_name", nullable = false, length = 50)
	private String invoiceName;
	
	@JoinColumn(name = "inv_hot_id", nullable = false)
	@ManyToOne
	private Hotel hotel;
	
	@JoinColumn(name = "inv_acc_id", nullable = false)
	@ManyToOne
	private Account account;
	
	@JsonIgnore
	@Column(name = "inv_file")
	private Byte[] invoiceFile;
	

	public Long getId() {
		return id;
	}
	
	

	public String getInvoiceName() {
		return invoiceName;
	}



	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}



	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Byte[] getInvoiceFile() {
		return invoiceFile;
	}

	public void setInvoiceFile(Byte[] invoiceFile) {
		this.invoiceFile = invoiceFile;
	}
	
}
