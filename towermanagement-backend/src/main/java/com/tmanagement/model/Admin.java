package com.tmanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="admin", uniqueConstraints=
@UniqueConstraint(columnNames={"email"}))
public class Admin {
	
	
	@Id
	@GeneratedValue(generator="mygen")
	@GenericGenerator( name="mygen", strategy="foreign", 
						parameters= {@Parameter(name="property", value="user")})
	public int adminId;
	
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private User user;
	

	public String name;
	

	
	public String email;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	
	
}
