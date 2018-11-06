package com.tmanagement.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Tower")
public class Tower {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int towerId;
	public String circle;
	public String address;
	
	public String engineerAssc;
	public String lease;
	public double latitude;
	public double longitude;
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Tower_Company", 
        joinColumns = { @JoinColumn(name = "towerId") }, 
        inverseJoinColumns = { @JoinColumn(name = "cmpnyId") })
	public List<Company> companies;
	public String software;
	public String hardware;
	public boolean status;
	
	@ManyToOne
	@JoinColumn(name="csaId")
	public CSA csa;
	
	@JsonIgnore
	@OneToMany(mappedBy="tower",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	public List<Complaint>complaints;
	
	public Tower() {
		super();
	}
	public Tower(int towerId, String circle, String address, String engineerAssc, String lease,
			double latitude, double longitude, List<Company> companies, String software, String hardware,
			boolean status) {
		super();
		this.towerId = towerId;
		this.circle = circle;
		this.address = address;
		
		this.engineerAssc = engineerAssc;
		this.lease = lease;
		this.latitude = latitude;
		this.longitude = longitude;
		this.companies = companies;
		this.software = software;
		this.hardware = hardware;
		this.status = status;
	}
	
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getTowerId() {
		return towerId;
	}
	public void setTowerId(int towerId) {
		this.towerId = towerId;
	}
	public String getCircle() {
		return circle;
	}
	public void setCircle(String circle) {
		this.circle = circle;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public List<Company> getCompanies() {
		return companies;
	}
	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}
	public CSA getCsa() {
		return csa;
	}
	public void setCsa(CSA csa) {
		this.csa = csa;
	}
	public List<Complaint> getComplaints() {
		return complaints;
	}
	public void setComplaints(List<Complaint> complaints) {
		this.complaints = complaints;
	}
	public String getEngineerAssc() {
		return engineerAssc;
	}
	public void setEngineerAssc(String engineerAssc) {
		this.engineerAssc = engineerAssc;
	}
	public String getLease() {
		return lease;
	}
	public void setLease(String lease) {
		this.lease = lease;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public List<Company> getCompany() {
		return companies;
	}
	public void setCompany(List<Company> companies) {
		this.companies = companies;
	}
	public String getSoftware() {
		return software;
	}
	public void setSoftware(String software) {
		this.software = software;
	}
	public String getHardware() {
		return hardware;
	}
	public void setHardware(String hardware) {
		this.hardware = hardware;
	}
	
	
}
