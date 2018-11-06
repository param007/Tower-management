package com.tmanagement.service;

import java.util.List;

import com.tmanagement.model.Complaint;

public interface ComplaintService {
	public List<Complaint> getComplaintByCsa(int inputCsaId);
	public List<Complaint> getComplaintByTower(int inputTowerId);
	public List<Complaint> fetchComplaints();
	
	public Complaint getComplaintByComplaintId(int complaintId);
	
	public Complaint addComplaint(Complaint complaint);
	public Complaint updateComplaint(Complaint complaint);
}
