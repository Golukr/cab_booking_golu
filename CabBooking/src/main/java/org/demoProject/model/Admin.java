package org.demoProject.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
public class Admin extends AbstractUser {

	@Id
	@javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	private Integer adminId;

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin( String userName, String password, String mobileNumber, String email) {
		super(userName, password, mobileNumber, email);
		// TODO Auto-generated constructor stub
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + "] | " + super.toString();
	}

}
