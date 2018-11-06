package com.tmanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmanagement.dao.ComplaintDAO;
import com.tmanagement.model.Complaint;
import com.tmanagement.model.ComplaintActionStatus;
@Service
public class ComplaintServiceImplementation implements ComplaintService {

	@Autowired
	public ComplaintDAO complaintRepo;

	@Override
	public List<Complaint> getComplaintByCsa(int inputCsaId) {
		List<Complaint> allComplaints = complaintRepo.findAllByOrderByDateOfIssueDesc();
		List<Complaint> csaComplaints = new ArrayList<>();

		for (Complaint comp : allComplaints) {
			if (comp.csa.getCsaId() == inputCsaId) {
				csaComplaints.add(comp);
			}
		}
		return csaComplaints;
	}

	@Override
	public List<Complaint> getComplaintByTower(int inputTowerId) {
		List<Complaint> allComplaints = complaintRepo.findAllByOrderByDateOfIssueDesc();
		List<Complaint> towerComplaints = new ArrayList<>();

		for (Complaint comp : allComplaints) {
			if (comp.tower.getTowerId() == inputTowerId) {
				towerComplaints.add(comp);
			}
		}
		return towerComplaints;
	}


	@Override
	public List<Complaint> fetchComplaints() {
		return complaintRepo.findAllByOrderByDateOfIssueDesc();
	}

	@Override
	public Complaint getComplaintByComplaintId(int complaintId) {
		Complaint complaint = null;

		Optional<Complaint> opComplaint = complaintRepo.findById(complaintId);
		if (opComplaint.isPresent()) {
			complaint = opComplaint.get();
		}

		return complaint;
	}

	@Override
	public Complaint addComplaint(Complaint complaint) {
		complaint.setActionStatus(ComplaintActionStatus.NEW);
		return complaintRepo.save(complaint);
	}



	@Override
	public Complaint updateComplaint(Complaint complaint) {
		return complaintRepo.save(complaint);
	}

}
