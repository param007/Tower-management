package com.tmanagement.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

	@Entity
	public class User {

	    @Id
	    @GeneratedValue(strategy= GenerationType.AUTO)
	    private int id;
	    
	   
	    @OneToOne( mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	    private Admin admin;
	    
	    @OneToOne(mappedBy="userr",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
		private CSA csa;
	    
	    
	    @Column
	    private String username;
	    @Column
	    @JsonIgnore
	    private String password;

	
	    private String role;


	    public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

}
