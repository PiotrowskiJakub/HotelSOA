package pl.edu.agh.soa.core.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @author Piotr Konsek
 * 
 * class holds all address data
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Address implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="add_id")
	private long id;
	
	@NotNull
	@Column(name="add_street", length=50)
	private String street;
	
	@NotNull
	@Column(name="add_house_number", length=10)
	private String houseNumber;
	
	@Column(name="add_flat_number")
	private Integer flatNumber;
	
	@NotNull
	@Column(name="add_postal_code", length=5)
	private String postalCode;
	
	@NotNull
	@Column(name="add_city", length=50)
	private String city;
	
	@Column(name="add_state", length=50)
	private String state;
	
	@NotNull
	@Column(name="add_country", length=50)
	private String country;
	
	@Column(name="add_latitude")
	private Double latitude;
	
	@Column(name="add_longitude")
	private Double longitude;
	
//	@JsonBackReference
//	@OneToMany(mappedBy="address", cascade=CascadeType.ALL)
//	private Set<Account> accounts = new HashSet<Account>(0);
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getHouseNumber() {	
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public Integer getFlatNumber() {
		return flatNumber;
	}
	public void setFlatNumber(Integer flatNumber) {
		this.flatNumber = flatNumber;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
//	public Set<Account> getAccounts() {
//		return accounts;
//	}
//	public void setAccounts(Set<Account> accounts) {
//		this.accounts = accounts;
//	}
}
