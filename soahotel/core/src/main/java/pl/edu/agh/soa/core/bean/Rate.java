package pl.edu.agh.soa.core.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * @author Piotr Konsek
 *
 */
@Entity
public class Rate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="rat_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="acc_id")
	private Account accountId;
	
	@ManyToOne
	@JoinColumn(name="hot_id")
	private Hotel hotel;
	
	@NotNull
	@Column(name="rat_value")
	private Integer rate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Account getAccountId() {
		return accountId;
	}

	public void setAccountId(Account accountId) {
		this.accountId = accountId;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}
	

}
