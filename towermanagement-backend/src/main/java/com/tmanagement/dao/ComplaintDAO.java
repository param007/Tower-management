package com.tmanagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tmanagement.model.Complaint;

@Repository
public interface ComplaintDAO extends JpaRepository<Complaint, Integer> {


	public List<Complaint> getComplaintByCsa(int inputCsaId);
	public List<Complaint> getComplaintByTower(int inputTowerId);
	public Complaint getComplaintByComplaintId(int complaintId);
	
	public List<Complaint> findAllByOrderByDateOfIssueDesc();
}
