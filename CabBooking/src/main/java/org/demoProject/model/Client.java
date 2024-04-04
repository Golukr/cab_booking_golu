package org.demoProject.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
public class Client {
	@Id
	@javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	Integer clientId;
	String name;
	String statement;
	String imagePath;

	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client(Integer clientId, String name, String statement, String imagePath) {
		super();
		this.clientId = clientId;
		this.statement = statement;
		this.name = name;
		this.imagePath = imagePath;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", name=" + name + ", statement=" + statement + ", imagePath="
				+ imagePath + "]";
	}

	

	
	
	
}
