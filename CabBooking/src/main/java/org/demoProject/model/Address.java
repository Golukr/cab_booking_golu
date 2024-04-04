package org.demoProject.model;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
public class Address {
	
	@Id
	private Integer addressId;
	private String location1;
	private String location2;
	private Float distance;
	private LocalTime datetime;
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Address(Integer addressId, String location1, String location2, Float distance, LocalTime datetime) {
		super();
		this.addressId = addressId;
		this.location1 = location1;
		this.location2 = location2;
		this.distance = distance;
		this.datetime = datetime;
	}
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	public String getLocation1() {
		return location1;
	}
	public void setLocation1(String location1) {
		this.location1 = location1;
	}
	public String getLocation2() {
		return location2;
	}
	public void setLocation2(String location2) {
		this.location2 = location2;
	}
	public Float getDistance() {
		return distance;
	}
	public void setDistance(Float distance) {
		this.distance = distance;
	}
	public LocalTime getDatetime() {
		return datetime;
	}
	public void setDatetime(LocalTime datetime) {
		this.datetime = datetime;
	}
	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", location1=" + location1 + ", location2=" + location2
				+ ", distance=" + distance + ", datetime=" + datetime + "]";
	}
	
	

}
