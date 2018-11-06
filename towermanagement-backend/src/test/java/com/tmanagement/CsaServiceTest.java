package com.tmanagement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.tmanagement.dao.CsaDAO;
import com.tmanagement.model.CSA;
import com.tmanagement.model.Complaint;
import com.tmanagement.model.Tower;
import com.tmanagement.model.User;
import com.tmanagement.service.CsaServiceImplementation;

public class CsaServiceTest {
	
	@Mock
    private  CsaDAO daoMock;
 	
    @InjectMocks
    private CsaServiceImplementation service;
   
    @Before
    public void setUp() throws Exception {
         MockitoAnnotations.initMocks(this);
    }
    
    
    @Test
    public void testFetchComplaints() {
    	
    	List<CSA> csaList = new ArrayList<>();
    	csaList.add(new CSA());
    	when(daoMock.findAll()).thenReturn(csaList);
    	assertThat(service.fetchAllCsa(), is(notNullValue()));
    	
    }
    
    @Test
    public void  testGetCsaByCsaId() {
    	
    	int csaId = 2;
    	
    	List<Complaint> complaintList = new ArrayList<>();
    	List<Tower> towerList = new ArrayList<>();
    	
    	towerList.add(new Tower());
    	complaintList.add(new Complaint());
    	
    	CSA csa = new CSA();
    	
    	csa.setCsaId(2);
    	csa.setCircle("Chennai");
    	csa.setEmail("dikshashresth@gmail.com");
    	csa.setName("Diksha");
    	csa.setComplaints(complaintList);
    	csa.setUserr(new User());
    	csa.setTowers(towerList);
    	
    	Optional<CSA> csaOp = Optional.of(csa);
    	
    	
    	
    	when(daoMock.findById(csaId)).thenReturn(csaOp);
    	assertThat(service.getCsaByCsaId(csaId), is(notNullValue()));
    	
    }
    
    @Test
    public void testFindByEmail() {
		String email = "dikshashresth@gmail.com";
	
    	CSA csa = new CSA();
		
    	List<Complaint> complaintList = new ArrayList<>();
    	List<Tower> towerList = new ArrayList<>();
    	
    	towerList.add(new Tower());
    	complaintList.add(new Complaint());
    	
    	csa.setCsaId(2);
    	csa.setCircle("Chennai");
    	csa.setEmail("dikshashresth@gmail.com");
    	csa.setName("Diksha");
    	csa.setComplaints(complaintList);
    	csa.setUserr(new User());
    	csa.setTowers(towerList);
		

		when(daoMock.findByEmail(email)).thenReturn(csa);
		
		assertThat(service.findByEmail(email)).isNotNull();
    }
    

}
