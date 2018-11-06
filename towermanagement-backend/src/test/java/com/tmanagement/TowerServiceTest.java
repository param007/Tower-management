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

import com.tmanagement.dao.TowerDAO;
import com.tmanagement.model.CSA;
import com.tmanagement.model.Tower;
import com.tmanagement.model.User;
import com.tmanagement.service.TowerService;
import com.tmanagement.service.TowerServiceImplementation;


public class TowerServiceTest {
	@Mock
    private TowerDAO daoMock;
 	
    @InjectMocks
    private TowerServiceImplementation service;
   
    @Before
    public void setUp() throws Exception {
         MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testGetTowerByCircle() {
    	
    	String circle = "Chennai";
    	List<Tower> towerList = new ArrayList<>();
    	
    	towerList.add(new Tower());
    	when(daoMock.getTowerByCircle(circle)).thenReturn(towerList);
    	assertThat(service.getTowerByCircle(circle), is(notNullValue()));
    	
    }
    
    @Test
    public void testGetTowerByCsaId() {
    	
    	int csaId=2;
    	List<Tower> towerList = new ArrayList<>();
    	
    	Tower tower = new Tower();
    	CSA csa = new CSA();
    	// csa.setCsaId(2);
    	csa.setUserr(new User());
    	
    	tower.setTowerId(1);
    	tower.setAddress("Olympia");
    	tower.setCircle("Chennai");
    	tower.setCompanies(null);
    	tower.setCompany(null);
    	tower.setCsa(csa);
    	tower.setEngineerAssc("Stephen");
    	tower.setLatitude(67.8);
    	tower.setLease("farm");
    	tower.setLongitude(153.5);
    	tower.setSoftware("adobe");
    	tower.setStatus(false);
    	tower.setHardware("HW");
    	
    	towerList.add(tower);
    	
    	when(daoMock.findAll()).thenReturn(towerList);
    	assertThat(service.getTowerByCsaId(csaId), is(notNullValue()));
    	
    }
    
    @Test
    public void testGetTowerByTowerId() {
    	
    	int towerId = 1;
    	
    	Tower tower = new Tower();
    	
    	tower.setTowerId(towerId);
    	tower.setAddress("Olympia");
    	tower.setCircle("Chennai");
    	tower.setCompanies(null);
    	tower.setCompany(null);
    	tower.setCsa(null);
    	tower.setEngineerAssc("Stephen");
    	tower.setLatitude(67.8);
    	tower.setLease("farm");
    	tower.setLongitude(153.5);
    	tower.setSoftware("adobe");
    	tower.setStatus(false);
    	tower.setHardware("HW");
    	
    	Optional<Tower> towerOp = Optional.of(tower);
    	
    	when(daoMock.findById(towerId)).thenReturn(towerOp);
    	assertThat(service.getTowerByTowerId(towerId), is(notNullValue()));
    }
    
    @Test
    public void testAllTowers() {
    	
    	List<Tower> towerList = new ArrayList<>();
    	
    	towerList.add(new Tower());
    	
    	when(daoMock.findAll()).thenReturn(towerList);
    	assertThat(service.allTowers(), is(notNullValue()));
    	
    }
    
    @Test
    public void testChangeTowerStatus () {
    	
    	String change = "status changed";
    	
    	Tower tower = new Tower();
    	
    	tower.setTowerId(1);
    	tower.setAddress("Olympia");
    	tower.setCircle("Chennai");
    	tower.setCompanies(null);
    	tower.setCompany(null);
    	tower.setCsa(null);
    	tower.setEngineerAssc("Stephen");
    	tower.setLatitude(67.8);
    	tower.setLease("farm");
    	tower.setLongitude(153.5);
    	tower.setSoftware("adobe");
    	tower.setStatus(true);
    	tower.setHardware("HW");
    	
    	
    	when(daoMock.save(tower)).thenReturn(tower);
    	assertThat(service.changeTowerStatus(tower), is(notNullValue()));
    	
    }
    
}
