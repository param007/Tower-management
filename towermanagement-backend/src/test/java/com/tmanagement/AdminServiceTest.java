package com.tmanagement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.tmanagement.dao.AdminDao;
import com.tmanagement.model.Admin;
import com.tmanagement.model.User;
import com.tmanagement.service.AdminServImpl;


public class AdminServiceTest {

	 	@Mock
	    private AdminDao daoMock;
	 	
	    @InjectMocks
	    private AdminServImpl service;
	   
	    @Before
	    public void setUp() throws Exception {
	         MockitoAnnotations.initMocks(this);
	    }
	    
	    @Test
	    public void testGetAllAdmins() {
	    	List<Admin> adminList = new ArrayList<>();
			adminList.add(new Admin());
	    	
	    	when(daoMock.findAll()).thenReturn(adminList);
           
            assertThat(service.getAllAdmins(), is(notNullValue()));
	    }
	    
	    @Test
	    public void testFindByEmail() {
			String email = "deveshchaudhary2504@gmail.com";
		

			Admin admin = new Admin();

			admin.setAdminId(1);
			admin.setEmail(email);
			admin.setName("Devesh");
			admin.setUser(new User());
			

			when(daoMock.findByEmail(email)).thenReturn(admin);
			
			assertThat(service.findByEmail(email)).isNotNull();
	    }
}
