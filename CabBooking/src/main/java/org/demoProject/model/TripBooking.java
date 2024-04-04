package org.demoProject.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;



@Component
@Scope("prototype")
@Entity
public class TripBooking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tripBookingId;
	private String fromLocation;
	private String toLocation;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime fromDateTime;
	private LocalDateTime toDateTime;
	private Boolean status;
	private Float distanceInKm;
	private Float bill;

	@OneToOne
	private Customer customerId;

	@ManyToOne
	private Driver driverId;

	public TripBooking() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TripBooking(String fromLocation, String toLocation, LocalDateTime fromDateTime, LocalDateTime toDateTime,
			Boolean status, Float distanceInKm, Float bill, Customer customerId, Driver driverId) {
		super();
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.fromDateTime = fromDateTime;
		this.toDateTime = toDateTime;
		this.status = status;
		this.distanceInKm = distanceInKm;
		this.bill = bill;
		this.customerId = customerId;
		this.driverId = driverId;
	}

	public Integer getTripBookingId() {
		return tripBookingId;
	}

	public void setTripBookingId(Integer tripBookingId) {
		this.tripBookingId = tripBookingId;
	}

	public String getFromLocation() {
		return fromLocation;
	}

	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}

	public String getToLocation() {
		return toLocation;
	}

	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}
	public LocalDateTime getFromDateTime() {
		return fromDateTime;
	}

	public void setFromDateTime(LocalDateTime fromDateTime) {
		this.fromDateTime = fromDateTime;
	}

	public LocalDateTime getToDateTime() {
		return toDateTime;
	}

	public void setToDateTime(LocalDateTime toDateTime) {
		this.toDateTime = toDateTime;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Float getDistanceInKm() {
		return distanceInKm;
	}

	public void setDistanceInKm(Float distanceInKm) {
		this.distanceInKm = distanceInKm;
	}

	public Float getBill() {
		return bill;
	}

	public void setBill(Float bill) {
		this.bill = bill;
	}

	public Customer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Customer customerId) {
		this.customerId = customerId;
	}

	public Driver getDriverId() {
		return driverId;
	}

	public void setDriverId(Driver driverId) {
		this.driverId = driverId;
	}

	@Override
	public String toString() {
		return "TripBooking [tripBookingId=" + tripBookingId + ", fromLocation=" + fromLocation + ", toLocation="
				+ toLocation + ", fromDateTime=" + fromDateTime + ", toDateTime=" + toDateTime + ", status=" + status
				+ ", distanceInKm=" + distanceInKm + ", bill=" + bill + ", customerId=" + customerId + ", driverId="
				+ driverId + "]";
	}


	
}
