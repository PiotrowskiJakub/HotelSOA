package pl.edu.agh.soa.core.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

/**
 * @author Piotr Konsek
 *
 */
@Embeddable
public class Complaint implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	
	@NotNull
	@Column(name="com_description")
	@Type(type="TEXT")
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
