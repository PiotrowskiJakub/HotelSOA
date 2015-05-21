package pl.edu.agh.soa.core.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * @author Piotr Konsek
 *
 */
@Entity
public class Hotel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="hot_id")
	private long id;
	
	@NotNull
	@Column(name="hot_add_id")
	private Address address;
	
	@NotNull
	@Column(name="hot_con_id")
	private Contact contact;
	
	@Column(name="hot_avg_rate", precision=1, scale=2)
	private Double averageRate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Double getAverageRate() {
		return averageRate;
	}

	public void setAverageRate(Double averageRate) {
		this.averageRate = averageRate;
	}

}
