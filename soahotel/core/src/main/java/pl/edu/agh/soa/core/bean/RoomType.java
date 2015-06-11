package pl.edu.agh.soa.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Type;

/**
 * 
 * @author Jakub Piotrowsk
 *
 */
@Entity
@Table(name="room_types")
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoomType implements Serializable {
	@Id
	@GeneratedValue
	@Column(name="rty_id")
	private Long id;
	@Column(name="rty_name", length=50, nullable=false)
	private String name;
	@Column(name="rty_description", nullable=false)
	@Type(type="text")
	private String description;
	@Column(name="dst_price", nullable=false, precision=7, scale=2)
	private BigDecimal price;
	
	@JsonBackReference
	@JsonIgnore
	@OneToMany(mappedBy="roomType")
	private Set<Room> rooms = new HashSet<>(0);
	
	public RoomType() {
	}

	public RoomType(Long id, String name, String description, BigDecimal price) {
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

	public Set<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}
}
