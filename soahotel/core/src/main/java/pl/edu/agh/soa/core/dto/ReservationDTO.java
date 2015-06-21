package pl.edu.agh.soa.core.dto;

import java.util.Date;
import java.util.List;

/**
 * @author Piotr Konsek
 *
 */
public class ReservationDTO {

	private Long id;	
	private Long complaintId;	
	private Long roomTypeId;	
	private Long hotelId;
	private Long accountId;	
	private Long discountId;	
	private Date startDate;	
	private Date endDate;	
	private List<Long> additionalServicesID;
	
	public Long getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(Long roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(Long complaintId) {
		this.complaintId = complaintId;
	}

	public Long getRoomId() {
		return roomTypeId;
	}

	public void setRoomId(Long roomId) {
		this.roomTypeId = roomId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getDiscountId() {
		return discountId;
	}

	public void setDiscountId(Long discountId) {
		this.discountId = discountId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<Long> getAdditionalServicesID() {
		return additionalServicesID;
	}

	public void setAdditionalServicesID(List<Long> additionalServicesID) {
		this.additionalServicesID = additionalServicesID;
	}
	
	
}
