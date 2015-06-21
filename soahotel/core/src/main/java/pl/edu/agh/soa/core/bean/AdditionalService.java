package pl.edu.agh.soa.core.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author Jakub Piotrowski
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="additional_services")
public class AdditionalService implements Serializable {
	@Id
	@GeneratedValue
	@Column(name="ase_id")
	private Long id;
	@ManyToOne
	@JoinColumn(name="ase_ast_id", nullable=false)
	private AdditionalServiceType serviceType;
	@ManyToOne
	@JoinColumn(name="ase_res_id", nullable=false)
	private Reservation reservation;
	@Column(name="ase_date", nullable=false)
	private Date date;
	@Column(name="ase_count", nullable=false)
	private Integer count = 1;
	
	public AdditionalService() {
	}

	public AdditionalService(Long id, AdditionalServiceType serviceType,
			Reservation reservation, Date date, Integer count) {
		super();
		this.id = id;
		this.serviceType = serviceType;
		this.reservation = reservation;
		this.date = date;
		this.count = count;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AdditionalServiceType getServiceType() {
		return serviceType;
	}

	public void setServiceType(AdditionalServiceType serviceType) {
		this.serviceType = serviceType;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
