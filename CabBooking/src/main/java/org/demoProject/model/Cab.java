package org.demoProject.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("prototype")
@Entity
public class Cab {

	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	private Integer cabId;
	private String carType;
	private Float perKmRate;

	public Cab() {
		
		// TODO Auto-generated constructor stub
	}

	public Cab(String carType, Float perKmRate) {
		
		this.carType = carType;
		this.perKmRate = perKmRate;
	}

	

	public Integer getCabId() {
		return cabId;
	}

	public void setCabId(Integer cabId) {
		this.cabId = cabId;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public Float getPerKmRate() {
		return perKmRate;
	}

	public void setPerKmRate(Float perKmRate) {
		this.perKmRate = perKmRate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(carType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cab other = (Cab) obj;
		return Objects.equals(carType, other.carType);
	}

	@Override
	public String toString() {
		return "Cab [cabId=" + cabId + ", carType=" + carType + ", perKmRate=" + perKmRate + "]";
	}

}
