package pl.edu.agh.soa.core.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
 * @author Piotr Konsek
 *
 */
@Entity
@Table(name = "report_type")
public class ReportType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 298767906947417146L;

	@Id
	@GeneratedValue
	@Column(name = "ret_id")
	private Long id;
	
	@Column(name = "ret_name", length = 50, nullable = false)
	private String name;
	
	@Column(name = "ret_description")
	@Type(type = "text")
	private String description;

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
}
