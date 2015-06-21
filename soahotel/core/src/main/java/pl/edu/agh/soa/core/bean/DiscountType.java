package pl.edu.agh.soa.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
 * 
 * @author Jakub Piotrowski
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "discount_types")
public class DiscountType implements Serializable {
	@Id
	@GeneratedValue
	@Column(name = "dst_id")
	private Long id;
	
	@Column(name = "dst_name", length = 50, nullable = false)
	private String name;
	
	@Column(name = "dst_description", nullable = false)
	@Type(type="text")
	private String description;
	
	@Column(name = "dst_price", nullable = false, precision=7, scale=2)
	private BigDecimal price;
	
//	@OneToMany(mappedBy="discountType")
//	private Set<Reservation> reservations = new HashSet<>(0);

	public DiscountType() {
	}

	public DiscountType(Long id, String name, String description,
			BigDecimal price) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
//
//	public Set<Reservation> getReservations() {
//		return reservations;
//	}
//
//	public void setReservations(Set<Reservation> reservations) {
//		this.reservations = reservations;
//	}
}
