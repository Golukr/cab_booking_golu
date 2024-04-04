package org.demoProject.dto;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("loginToken")
@Scope("session")
public class LoginToken {
	
	private String userName;
	private String password;
	
	public LoginToken() {
		
	}

	public LoginToken(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	@Override
	public String toString() {
		return "LoginToken [userName=" + userName + ", password=" + password + "]";
	}
	
	

}
