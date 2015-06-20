package pl.edu.agh.soa.core.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 
 * @author Jakub Piotrowski
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="reservation")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Reservation implements Serializable {
	@Id
	@GeneratedValue
	@Column(name="res_id")
	private Long id;
	
	@Embedded
	private Complaint complaint;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name="res_roo_id", nullable=false)
	private Room room;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name="res_acc_id", nullable=false)
	private Account account;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name="res_dst_id")
	private DiscountType discountType;
	
	@Column(name="res_start_date", nullable=false)
	private Date startDate;
	
	@Column(name="res_end_date", nullable=false)
	private Date endDate;
	
	@Column(name="res_paid", nullable=false)
	private Boolean paid = false;
	
	@OneToMany(mappedBy="reservation", fetch=FetchType.EAGER)
	private Set<AdditionalService> additionalServices = new HashSet<>(0);
	
	public Reservation() {
	}

	public Reservation(Long id, Room room, Account account, Date startDate,
			Date endDate, Boolean paid) {
		super();
		this.id = id;
		this.room = room;
		this.account = account;
		this.startDate = startDate;
		this.endDate = endDate;
		this.paid = paid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public DiscountType getDiscountType() {
		return discountType;
	}

	public void setDiscountType(DiscountType discountType) {
		this.discountType = discountType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Boolean getPaid() {
		return paid;
	}

	public void setPaid(Boolean paid) {
		this.paid = paid;
	}

	public Set<AdditionalService> getAdditionalServices() {
		return additionalServices;
	}

	public void setAdditionalServices(Set<AdditionalService> additionalServices) {
		this.additionalServices = additionalServices;
	}
}
