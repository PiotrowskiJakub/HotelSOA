package pl.edu.agh.soa.core.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Piotr Konsek
 *
 */
@Entity
@Table(name = "report")
public class Report implements Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -151609919477565590L;

	@Id
	@GeneratedValue
	@Column(name = "rep_id")
	private Long id;
	
	@JoinColumn(name = "rep_ret_id")
	@ManyToOne
	private ReportType reportType;
	
//	@JoinColumn(name = "rep_emp_id")
//	@ManyToOne
//	private Employee employeeId;
	
	@Column(name= "rep_document")
	private byte [] document;
	
	public Long getId() {
		return id;
	}
	public ReportType getReportType() {
		return reportType;
	}
	public void setReportType(ReportType reportType) {
		this.reportType = reportType;
	}
//	public Employee getEmployeeId() {
//		return employeeId;
//	}
//	public void setEmployeeId(Employee employeeId) {
//		this.employeeId = employeeId;
//	}
	public byte[] getReport() {
		return document;
	}
	public void setReport(byte[] report) {
		this.document = report;
	}
}
