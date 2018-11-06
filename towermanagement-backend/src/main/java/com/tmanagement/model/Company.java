package com.tmanagement.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Company")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int cmpnyId;
	public String cmpnyName;
	
	@JsonIgnore
	@ManyToMany(fetch=FetchType.LAZY,
			cascade= {CascadeType.ALL},
			mappedBy = "companies")
	public List<Tower> towers=new ArrayList<>();
	
	public Company() {
		super();
	}

	public String getCmpnyName() {
		return cmpnyName;
	}

	public void setCmpnyName(String cmpnyName) {
		this.cmpnyName = cmpnyName;
	}

	public Company(int cmpnyId, String cmpnyName, List<Tower> towers) {
		super();
		this.cmpnyId = cmpnyId;
		this.cmpnyName = cmpnyName;
		this.towers = towers;
	}

	public int getCmpnyId() {
		return cmpnyId;
	}

	public void setCmpnyId(int cmpnyId) {
		this.cmpnyId = cmpnyId;
	}

	

	public List<Tower> getTowers() {
		return towers;
	}

	public void setTowers(List<Tower> towers) {
		this.towers = towers;
	}
	
	
}
