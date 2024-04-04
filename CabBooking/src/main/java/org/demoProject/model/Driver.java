package org.demoProject.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
public class Driver extends AbstractUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer driverId;
	private String licenceNo;
	private Float rating;

	@OneToOne
	private Cab cab;

	public Driver() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Driver( String licenceNo, Float rating, Cab cab, String userName, String password,
			String mobileNumber, String email) {
		super(userName, password, mobileNumber, email);
		this.licenceNo = licenceNo;
		this.rating = rating;
		this.cab = cab;
		// TODO Auto-generated constructor stub
	}

	public Integer getDriverId() {
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

	public String getLicenceNo() {
		return licenceNo;
	}

	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	

	public Cab getCab() {
		return cab;
	}

	public void setCab(Cab cab) {
		this.cab = cab;
	}

	@Override
	public String toString() {
		return "Driver [driverId=" + driverId + ", licenceNo=" + licenceNo + ", rating=" + rating + ", cab="
				+ cab.getCabId() + "]";
	}

}
