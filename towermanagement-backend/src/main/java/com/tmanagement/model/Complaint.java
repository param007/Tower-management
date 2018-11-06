package com.tmanagement.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name="Complaint")
public class Complaint {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int complaintId;
	

	@ManyToOne
	@JoinColumn(name="csaId")
	public CSA csa;
	
	@ManyToOne
	@JoinColumn(name="towerId")
	public Tower tower;
	
	@Enumerated(EnumType.STRING)
	public ComplaintType type;
	public String description;
	
	@DateTimeFormat(iso=ISO.DATE)
	public LocalDate dateOfIssue;
	@DateTimeFormat(iso=ISO.DATE)
	public LocalDate dateOfApproval;
	public boolean viewStatus;
	
	@Enumerated(EnumType.STRING)
	public ComplaintActionStatus actionStatus;
	

	public int getComplaintId() {
		return complaintId;
	}
	public void setComplaintId(int complaintId) {
		this.complaintId = complaintId;
	}

	
	public CSA getCsa() {
		return csa;
	}
	public void setCsa(CSA csa) {
		this.csa = csa;
	}
	public Tower getTower() {
		return tower;
	}
	public void setTower(Tower tower) {
		this.tower = tower;
	}
	public ComplaintType getType() {
		return type;
	}
	public void setType(ComplaintType type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDateOfIssue() {
		return dateOfIssue;
	}
	public void setDateOfIssue(LocalDate dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}
	public LocalDate getDateOfApproval() {
		return dateOfApproval;
	}
	public void setDateOfApproval(LocalDate dateOfApproval) {
		this.dateOfApproval = dateOfApproval;
	}
	public boolean isViewStatus() {
		return viewStatus;
	}
	public void setViewStatus(boolean viewStatus) {
		this.viewStatus = viewStatus;
	}
	public ComplaintActionStatus getActionStatus() {
		return actionStatus;
	}
	public void setActionStatus(ComplaintActionStatus actionStatus) {
		this.actionStatus = actionStatus;
	}
	

	
	
	
}
