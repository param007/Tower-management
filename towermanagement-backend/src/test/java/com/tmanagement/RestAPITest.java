package com.tmanagement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.tmanagement.controller.RestAPI;
import com.tmanagement.model.Admin;
import com.tmanagement.model.CSA;
import com.tmanagement.model.Complaint;
import com.tmanagement.model.ComplaintActionStatus;
import com.tmanagement.model.ComplaintType;
import com.tmanagement.model.Tower;
import com.tmanagement.model.User;
import com.tmanagement.service.AdminService;
import com.tmanagement.service.ComplaintService;
import com.tmanagement.service.CsaService;
import com.tmanagement.service.TowerService;
import com.tmanagement.service.UserService;
import com.tmanagement.TestUtil.TestUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = RestAPI.class)
public class RestAPITest {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@MockBean
	private AdminService adminServiceMock;
	@MockBean
	private ComplaintService complaintServiceMock;
	@MockBean
	private CsaService csaServiceMock;
	@MockBean
	private TowerService towerServiceMock;
	@MockBean
	private UserService userServiceMock;
	@MockBean
	private BCryptPasswordEncoder bcryptEncoderMock;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@After
	public void tearDown() {
		mockMvc = null;
	}
	
	@Test
	public void testGetAllTowerByCircle() throws Exception {
		assertThat(this.towerServiceMock).isNotNull();
		
		List<Tower> towerList = new ArrayList<>();
		towerList.add(new Tower());
		
		when(towerServiceMock.getTowerByCircle("Chennai")).thenReturn(towerList);
		
		mockMvc.perform(get("/towermgmt/Circlee/Chennai")).andExpect(status().isOk()).andDo(print());
	}
	
	@Test
	public void testGetAllTowers() throws Exception {
		assertThat(this.towerServiceMock).isNotNull();
		
		List<Tower> towerList = new ArrayList<>();
		towerList.add(new Tower());
		
		when(towerServiceMock.allTowers()).thenReturn(towerList);
		
		mockMvc.perform(get("/towermgmt/Towers")).andExpect(status().isOk()).andDo(print());
	}
	
	@Test
	public void testGetTowerById() throws Exception {
		assertThat(this.towerServiceMock).isNotNull();
		
		Tower tower = new Tower();
		
		when(towerServiceMock.getTowerByTowerId(1)).thenReturn(tower);
		
		mockMvc.perform(get("/towermgmt/Towers/1")).andExpect(status().isOk()).andDo(print());
	}
	
	@Test
	public void testGetTowerByCsaId() throws Exception {
		assertThat(this.towerServiceMock).isNotNull();
		
		List<Tower> towerList = new ArrayList<>();
		towerList.add(new Tower());
		
		when(towerServiceMock.getTowerByCsaId(2)).thenReturn(towerList);
		mockMvc.perform(get("/towermgmt/Towers/csa/2")).andExpect(status().isOk()).andDo(print());
		
	}
	
	@Test
	public void testGetAllComplaints() throws Exception {
		assertThat(this.complaintServiceMock).isNotNull();
		
		List<Complaint> complaintList = new ArrayList<>();
		complaintList.add(new Complaint());
		
		when(complaintServiceMock.fetchComplaints()).thenReturn(complaintList);
		
		mockMvc.perform(get("/towermgmt/Complaints")).andExpect(status().isOk()).andDo(print());
	}
	
	@Test
	public void testGetComplaintById() throws Exception {
		assertThat(this.complaintServiceMock).isNotNull();
		
		Complaint comp = new Complaint();
		
		when(complaintServiceMock.getComplaintByComplaintId(1)).thenReturn(comp);
		
		mockMvc.perform(get("/towermgmt/Complaints/1")).andExpect(status().isOk()).andDo(print());
	}
	
	@Test
	public void testGetComplaintByCsaId() throws Exception {
		assertThat(this.complaintServiceMock).isNotNull();
		
		List<Complaint> complaintList = new ArrayList<>();
		complaintList.add(new Complaint());
		
		when(complaintServiceMock.getComplaintByCsa(2)).thenReturn(complaintList);
		
		mockMvc.perform(get("/towermgmt/Complaints/csa/2")).andExpect(status().isOk()).andDo(print());
	}
	
	@Test
	public void testGetComplaintByTowerId() throws Exception {
		assertThat(this.complaintServiceMock).isNotNull();
		
		List<Complaint> complaintList = new ArrayList<>();
		complaintList.add(new Complaint());
		
		when(complaintServiceMock.getComplaintByTower(1)).thenReturn(complaintList);
		
		mockMvc.perform(get("/towermgmt/Complaints/tower/1")).andExpect(status().isOk()).andDo(print());
	}
	
	@Test
	public void testAddComplaint() throws Exception {
		
		assertThat(this.complaintServiceMock).isNotNull();
		
		Complaint compAdded = new Complaint();
		Complaint comp = new Complaint();
		CSA csa = new CSA();
		Tower tower = new Tower();
		compAdded.setCsa(csa);
		compAdded.setTower(tower);
		compAdded.setType(ComplaintType.BATTERY);
		compAdded.setDescription("Constant Battery drain");
		compAdded.setDateOfIssue(null);
		compAdded.setDateOfApproval(null);
		compAdded.setViewStatus(false);
		compAdded.setActionStatus(ComplaintActionStatus.NEW);
		
		when(complaintServiceMock.addComplaint(Mockito.any(Complaint.class))).thenReturn(compAdded);
		
		mockMvc.perform(post("/towermgmt/addComplaint").contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(compAdded))).andDo(print()).andExpect(status().isOk());
				
	}
	
	@Test
	public void testViewStatusChange() throws Exception {
		assertThat(this.complaintServiceMock).isNotNull();
		
		Complaint compAdded = new Complaint();
		CSA csa = new CSA();
		csa.setCsaId(2);
		Tower tower = new Tower();
		tower.setTowerId(1);
		compAdded.setComplaintId(1);
		compAdded.setCsa(csa);
		compAdded.setTower(tower);
		compAdded.setType(ComplaintType.BATTERY);
		compAdded.setDescription("Constant Battery drain");
		compAdded.setDateOfIssue(null);
		compAdded.setDateOfApproval(null);
		compAdded.setViewStatus(true);
		compAdded.setActionStatus(ComplaintActionStatus.NEW);
		
		when(complaintServiceMock.updateComplaint(Mockito.any(Complaint.class))).thenReturn(compAdded);
		
		mockMvc.perform(put("/towermgmt/changeViewStatus").contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(compAdded))).andDo(print()).andExpect(status().isOk());
				
		
	}
	
	@Test
	public void testActionStatusChange() throws Exception {
		
		assertThat(this.complaintServiceMock).isNotNull();
		
		Complaint compAdded = new Complaint();
		CSA csa = new CSA();
		csa.setCsaId(2);
		Tower tower = new Tower();
		tower.setTowerId(1);
		compAdded.setComplaintId(1);
		compAdded.setCsa(csa);
		compAdded.setTower(tower);
		compAdded.setType(ComplaintType.BATTERY);
		compAdded.setDescription("Constant Battery drain");
		compAdded.setDateOfIssue(null);
		compAdded.setDateOfApproval(null);
		compAdded.setViewStatus(true);
		compAdded.setActionStatus(ComplaintActionStatus.ACCEPTED);
		
		when(complaintServiceMock.updateComplaint(Mockito.any(Complaint.class))).thenReturn(compAdded);
		
		mockMvc.perform(put("/towermgmt/changeActionStatus").contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(compAdded))).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void testTowerStatusChange() throws Exception {
		
		assertThat(this.towerServiceMock).isNotNull();
		
		Tower towerAdded = new Tower();
		CSA csa = new CSA();
		csa.setCsaId(4);
		towerAdded.setTowerId(2);
		towerAdded.setCircle("Hyderabad");
		towerAdded.setAddress("RMZ");
		towerAdded.setEngineerAssc("Param");
		towerAdded.setLease("sidco");
		towerAdded.setLatitude(65.8);
		towerAdded.setLongitude(103.5);
		towerAdded.setSoftware("google");
		towerAdded.setHardware("HW");
		towerAdded.setStatus(false);
		
		when(towerServiceMock.changeTowerStatus(Mockito.any(Tower.class))).thenReturn(towerAdded);
		
		mockMvc.perform(put("/towermgmt/changeTowerStatus").contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(towerAdded))).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void testGetCsaById() throws Exception {
		assertThat(this.csaServiceMock).isNotNull();
		
		CSA csa = new CSA();
		
		when(csaServiceMock.getCsaByCsaId(2)).thenReturn(csa);
		
		mockMvc.perform(get("/towermgmt/csa/2")).andExpect(status().isOk()).andDo(print());
	}
	
	@Test
	public void testGetAllCsa() throws Exception {
		assertThat(this.csaServiceMock).isNotNull();
		
		List<CSA> csaList = new ArrayList<>();
		csaList.add(new CSA());
		
		when(csaServiceMock.fetchAllCsa()).thenReturn(csaList);
		
		mockMvc.perform(get("/towermgmt/getAllCsa")).andExpect(status().isOk()).andDo(print());
	}
	
	@Test
	public void testForgotPassword() throws Exception {
		
		assertThat(this.adminServiceMock).isNotNull();
		assertThat(this.csaServiceMock).isNotNull();
		
		Admin adminAdded = new Admin();
		CSA csaAdded = new CSA();
		String otp = "1234";
		
		when(adminServiceMock.findByEmail("deveshchaudhary2504@gmail.com")).thenReturn(adminAdded);
		when(csaServiceMock.findByEmail("dikshashresth@gmail.com")).thenReturn(csaAdded);
		
		/*mockMvc.perform(put("/towermgmt/reset/deveshchaudhary2504@gmail.com").contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(otp))).andDo(print()).andExpect(status().isOk());*/
		
		
	}
	
	@Test
	public void testChangePassword() throws Exception {
		
		assertThat(this.userServiceMock).isNotNull();
		
		String password = "newpwd";
		User userAdded = new User();
		userAdded.setPassword(bcryptEncoderMock.encode(password));
		
		when(userServiceMock.findByUsername("Snorlxx")).thenReturn(userAdded);
		
		/*mockMvc.perform(post("/towermgmt/changepwd/Snorlxx").contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(userAdded))).andDo(print()).andExpect(status().isOk());*/
		
	}
	
}
