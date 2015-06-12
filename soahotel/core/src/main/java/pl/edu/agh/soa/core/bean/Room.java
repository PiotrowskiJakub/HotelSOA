package pl.edu.agh.soa.core.bean;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 
 * @author Jakub Piotrowski
 *
 */
@Entity
@Table(name="room")
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Room implements Serializable {
	@Id
	@GeneratedValue
	@Column(name="roo_id")
	private Long id;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="roo_hot_id", nullable=false)
	private Hotel hotel;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="roo_rty_id", nullable=false)
	private RoomType roomType;
	@Column(name="roo_floor", nullable=false)
	private Integer floor;
	@Column(name="roo_number", nullable=false)
	private Integer number;
	@Column(name="roo_size", nullable=false)
	private Integer size;
	
	public Room() {
	}

	public Room(Long id, RoomType roomType, Integer floor,
			Integer number, Integer size) {
		super();
		this.id = id;
		this.roomType = roomType;
		this.floor = floor;
		this.number = number;
		this.size = size;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public Integer getFloor() {
		return floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
}
