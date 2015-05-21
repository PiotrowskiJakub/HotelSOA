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
// TODO: Something wrong with this class, hibernate doesn't create a table
public class Complaint implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	
	@NotNull
	@Column(name="com_description")
	@Type(type="text")
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
