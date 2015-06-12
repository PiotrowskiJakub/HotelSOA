package pl.edu.agh.soa.core.bean;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
	@JoinColumn(name="hot_add_id")
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Address address;
	
	@NotNull
	@JoinColumn(name="hot_con_id")
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Contact contact;
	
	@Column(name="hot_avg_rate", precision=1, scale=2)
	private Double averageRate;
	
	@Column(name="hot_name", length=255)
	private String name;

	@Column(name="hot_desc", length=1000)
	private String desc;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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
