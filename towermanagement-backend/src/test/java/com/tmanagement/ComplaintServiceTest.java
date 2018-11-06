package com.tmanagement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.tmanagement.dao.ComplaintDAO;
import com.tmanagement.model.CSA;
import com.tmanagement.model.Complaint;
import com.tmanagement.model.ComplaintActionStatus;
import com.tmanagement.model.ComplaintType;
import com.tmanagement.model.Tower;
import com.tmanagement.service.ComplaintService;
import com.tmanagement.service.ComplaintServiceImplementation;

public class ComplaintServiceTest {
	@Mock
    private ComplaintDAO daoMock;
 	
    @InjectMocks
    private ComplaintServiceImplementation service;
   
    @Before
    public void setUp() throws Exception {
         MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testGetComplaintByCsa() {
    	
    	List<Complaint> complaintList = new ArrayList<>();
    	
    	Complaint complaint = new Complaint();
    	complaint.setComplaintId(1);
    	complaint.setActionStatus(ComplaintActionStatus.NEW);
    	complaint.setDateOfIssue(null);
    	complaint.setDateOfApproval(null);
    	complaint.setDescription("Constant Battery drain");
    	complaint.setType(ComplaintType.BATTERY);
    	complaint.setViewStatus(false);
    	complaint.setCsa(new CSA());
    	complaint.setTower(new Tower());
    	
    	complaintList.add(complaint);
    	
    	when(daoMock.findAllByOrderByDateOfIssueDesc()).thenReturn(complaintList);
   
    	assertThat(service.getComplaintByCsa(1), is(notNullValue()));
    	
    }
    
    @Test
    public void testGetComplaintByTower() {
    	
    	List<Complaint> complaintList = new ArrayList<>();
    	
    	Complaint complaint = new Complaint();
    	complaint.setComplaintId(1);
    	complaint.setActionStatus(ComplaintActionStatus.NEW);
    	complaint.setDateOfIssue(null);
    	complaint.setDateOfApproval(null);
    	complaint.setDescription("Constant Battery drain");
    	complaint.setType(ComplaintType.BATTERY);
    	complaint.setViewStatus(false);
    	complaint.setCsa(new CSA());
    	complaint.setTower(new Tower());
    	
    	complaintList.add(complaint);
    	
    	when(daoMock.findAllByOrderByDateOfIssueDesc()).thenReturn(complaintList);
    	
    	int inputTowerId = 1;
    	
    	assertThat(service.getComplaintByTower(inputTowerId), is(notNullValue()));
    
    }
    
    @Test
    public void testFetchComplaints() {
    	
    	List<Complaint> complaintList = new ArrayList<>();
    	complaintList.add(new Complaint());
    	when(daoMock.findAllByOrderByDateOfIssueDesc()).thenReturn(complaintList);
    	assertThat(service.fetchComplaints(), is(notNullValue()));
    	
    }
    
    @Test
    public void testGetComplaintByComplaintId() {
    	int complaintId  = 1;
    	
    	
    	
    	Complaint complaint = new Complaint();
    	complaint.setComplaintId(1);
    	complaint.setActionStatus(ComplaintActionStatus.NEW);
    	complaint.setDateOfIssue(null);
    	complaint.setDateOfApproval(null);
    	complaint.setDescription("Constant Battery drain");
    	complaint.setType(ComplaintType.BATTERY);
    	complaint.setViewStatus(false);
    	complaint.setCsa(new CSA());
    	complaint.setTower(new Tower());
    	Optional<Complaint> complainto = Optional.of(complaint);
    	
    	
    	when(daoMock.findById(complaintId)).thenReturn(complainto);
		
		assertThat(service.getComplaintByComplaintId(complaintId)).isNotNull();
    	
    }
    
    
    @Test 
    public void testAddComplaint() {
    	
    	when(daoMock.save(Mockito.any(Complaint.class))).thenReturn(new Complaint());
        
    	Complaint complaint = new Complaint();
        
    	assertThat(service.addComplaint(complaint), is(notNullValue()));
    	
    }
    
    @Test 
    public void testUpdateComplaint() {
    	
    	when(daoMock.save(Mockito.any(Complaint.class))).thenReturn(new Complaint());
        
    	Complaint complaint = new Complaint();
        
    	assertThat(service.addComplaint(complaint), is(notNullValue()));
    }
    
   
    

}
