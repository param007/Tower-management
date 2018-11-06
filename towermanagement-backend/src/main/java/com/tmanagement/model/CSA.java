package com.tmanagement.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="csa", uniqueConstraints=
@UniqueConstraint(columnNames={"email"}))
public class CSA {
	
	
	@Id
	@GeneratedValue(generator="mygen")
	@GenericGenerator( name="mygen", strategy="foreign", 
						parameters= {@Parameter(name="property", value="userr")})
	public int csaId;
	
	public String circle;
	public String name;

	
	@OneToOne
	@PrimaryKeyJoinColumn
	private User userr;
	
	@JsonIgnore
	@OneToMany(mappedBy="csa",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	public List<Tower>towers;
	
	@OneToMany(mappedBy="csa",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JsonIgnore
	public List<Complaint>complaints;
	

	public String email;
	
	
	
	public List<Tower> getTowers() {
		return towers;
	}
	public void setTowers(List<Tower> towers) {
		this.towers = towers;
	}
	public List<Complaint> getComplaints() {
		return complaints;
	}
	public void setComplaints(List<Complaint> complaints) {
		this.complaints = complaints;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	public int getCsaId() {
		return csaId;
	}
	public void setCsaId(int csaId) {
		this.csaId = csaId;
	}
	public User getUserr() {
		return userr;
	}
	public void setUserr(User userr) {
		this.userr = userr;
	}
	public String getCircle() {
		return circle;
	}
	public void setCircle(String circle) {
		this.circle = circle;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
