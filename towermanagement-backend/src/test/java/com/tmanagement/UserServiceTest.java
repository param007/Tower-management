package com.tmanagement;

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

import com.tmanagement.dao.UserDao;
import com.tmanagement.model.User;
import com.tmanagement.service.UserService;
import com.tmanagement.service.UserServiceImpl;


public class UserServiceTest {
	
	@Mock
    private  UserDao daoMock;
 	
    @InjectMocks
    private	UserServiceImpl service;
  
    @Before
    public void setUp() throws Exception {
         MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testFindAll() {
    	List<User> userList = new ArrayList<>();
    	
    	userList.add(new User());
    	
    	when(daoMock.findAll()).thenReturn(userList);
    	assertThat(service.findAll(), is(notNullValue()));   	
    }
    
    @Test
    public void testFindOne() {
    	String username = "Snorlxx";
    	
    	User user = new User();
    	user.setId(1);
    	user.setUsername(username);
    	user.setPassword("$2a$10$B8.BjkA5a9l9vFBnlSfneeCoMxnBguWq0vCvgw9C4.19SpJcCiOxi");
    	user.setRole("ADMIN");

    	when(daoMock.findByUsername(username)).thenReturn(user);
    	assertThat(service.findOne(username), is(notNullValue()));  
    }
    
    @Test
    public void testFindById() {
    	int userId = 1;
    	
    	User user = new User();
    	user.setId(1);
    	user.setUsername("Snorlxx");
    	user.setPassword("$2a$10$B8.BjkA5a9l9vFBnlSfneeCoMxnBguWq0vCvgw9C4.19SpJcCiOxi");
    	user.setRole("ADMIN");
    	
    	Optional<User> userOp = Optional.of(user);
    	
    	when(daoMock.findById(userId)).thenReturn(userOp);
    	assertThat(service.findById(userId), is(notNullValue()));  
    	
    }
    
    @Test 
    public void testUpdatePassword() {
    	User user = new User();
    	
    	user.setId(1);
    	user.setUsername("Snorlxx");
    	user.setPassword("adsfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdf");
    	user.setRole("ADMIN");
    	
    	when(daoMock.save(user)).thenReturn(user);
    	assertThat(service.updatePassword(user), is(notNullValue())); 
    }
    
    @Test
    public void testFindByUsername() {
    	String username = "Snorlxx";
    	
    	User user = new User();
    	user.setId(1);
    	user.setUsername(username);
    	user.setPassword("$2a$10$B8.BjkA5a9l9vFBnlSfneeCoMxnBguWq0vCvgw9C4.19SpJcCiOxi");
    	user.setRole("ADMIN");

    	when(daoMock.findByUsername(username)).thenReturn(user);
    	assertThat(service.findByUsername(username), is(notNullValue()));  
    
    }
}
