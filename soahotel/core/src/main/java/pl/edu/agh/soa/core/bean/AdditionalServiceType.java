package pl.edu.agh.soa.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
 * 
 * @author Jakub Piotrowski
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="additional_services_types")
public class AdditionalServiceType implements Serializable {
	@Id
	@GeneratedValue
	@Column(name="ast_id")
	private Long id;
	@Column(name="ast_name", length=50, nullable=false)
	private String name;
	@Column(name="ast_description", nullable=false)
	@Type(type="text")
	private String description;
	@Column(name="dst_price", nullable=false, precision=7, scale=2)
	private BigDecimal price;
	@OneToMany(mappedBy="serviceType")
	private Set<AdditionalService> additionalServices = new HashSet<>(0);

	public AdditionalServiceType() {
	}

	public AdditionalServiceType(Long id, String name, String description,
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

	public Set<AdditionalService> getAdditionalServices() {
		return additionalServices;
	}

	public void setAdditionalServices(Set<AdditionalService> additionalServices) {
		this.additionalServices = additionalServices;
	}
}
