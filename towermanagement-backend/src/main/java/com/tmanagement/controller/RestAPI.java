package com.tmanagement.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tmanagement.mail.SendMail;
import com.tmanagement.model.Admin;
import com.tmanagement.model.CSA;
import com.tmanagement.model.Complaint;
import com.tmanagement.model.Tower;
import com.tmanagement.model.User;
import com.tmanagement.service.AdminService;
import com.tmanagement.service.ComplaintService;
import com.tmanagement.service.CsaService;
import com.tmanagement.service.TowerService;
import com.tmanagement.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/towermgmt")
public class RestAPI {

	@Autowired
	public TowerService towerServ;

	@Autowired
	public ComplaintService complaintServ;
	
	@Autowired
	public CsaService csaServ;
	
	@Autowired
	public AdminService adminServ;

	@Autowired
	public UserService userServ;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	public SendMail sendMail = new SendMail();

	@GetMapping("/Circlee/{circle}")
	public ResponseEntity<List<Tower>> getAllTowerByCircle(@PathVariable("circle") String circle) {
		ResponseEntity<List<Tower>> resp;
		List<Tower> tower = null;

		tower = towerServ.getTowerByCircle(circle);

		if (tower == null)
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			resp = new ResponseEntity<>(tower, HttpStatus.OK);
		return resp;
	}

	@GetMapping("/Towers")
	public ResponseEntity<List<Tower>> getAllTowers() {
		return new ResponseEntity<>(towerServ.allTowers(), HttpStatus.OK);
	}

	@GetMapping("/Towers/{id}")
	public ResponseEntity<Tower> getTowerById(@PathVariable("id") int towerId) {
		ResponseEntity<Tower> resp;
		Tower tower = towerServ.getTowerByTowerId(towerId);

		if (tower == null) {
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			resp = new ResponseEntity<>(tower, HttpStatus.OK);
		}
		return resp;
	}

	@GetMapping("/Towers/csa/{id}")
	public ResponseEntity<List<Tower>> getTowerByCsaId(@PathVariable("id") int CsaId) {
		ResponseEntity<List<Tower>> resp;
		List<Tower> tower = towerServ.getTowerByCsaId(CsaId);

		if (tower == null) {
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			resp = new ResponseEntity<>(tower, HttpStatus.OK);
		}
		return resp;
	}

	@GetMapping("/Complaints")
	public ResponseEntity<List<Complaint>> getAllComplaints() {
		return new ResponseEntity<>(complaintServ.fetchComplaints(), HttpStatus.OK);
	}

	@GetMapping("/Complaints/{id}")
	public ResponseEntity<Complaint> getComplaintById(@PathVariable("id") int complaintId) {
		ResponseEntity<Complaint> resp;
		Complaint complaint = complaintServ.getComplaintByComplaintId(complaintId);

		if (complaint == null) {
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			resp = new ResponseEntity<>(complaint, HttpStatus.OK);
		}
		return resp;
	}

	@GetMapping("/Complaints/csa/{id}")
	public ResponseEntity<List<Complaint>> getComplaintByCsaId(@PathVariable("id") int CsaId) {
		ResponseEntity<List<Complaint>> resp;
		List<Complaint> complaint = complaintServ.getComplaintByCsa(CsaId);

		if (complaint == null) {
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			resp = new ResponseEntity<>(complaint, HttpStatus.OK);
		}
		return resp;
	}

	@GetMapping("/Complaints/tower/{id}")
	public ResponseEntity<List<Complaint>> getComplaintByTowerId(@PathVariable("id") int towerId) {
		ResponseEntity<List<Complaint>> resp;
		List<Complaint> complaint = complaintServ.getComplaintByTower(towerId);

		if (complaint == null) {
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			resp = new ResponseEntity<>(complaint, HttpStatus.OK);
		}
		return resp;
	}
	

	@GetMapping("/Complaints/tower/{id}/download")
    public ModelAndView exldownload(@PathVariable("id") int towerId) {
        return new ModelAndView("excelView","complaint", complaintServ.getComplaintByTower(towerId));
	}

	@PostMapping("/addComplaint")
	public ResponseEntity<Complaint> addComplaint(@RequestBody Complaint complaint) {
		ResponseEntity<Complaint> resp = null;

		complaint.setDateOfIssue(LocalDate.now());
		if (resp == null) {
			Complaint c = complaintServ.addComplaint(complaint);
			if (c == null)
				resp = new ResponseEntity<Complaint>(HttpStatus.BAD_REQUEST);
			else
				resp = new ResponseEntity<Complaint>(c, HttpStatus.OK);
		}
		return resp;
	}

	@PutMapping("/Complaints/changeViewStatus")
	public ResponseEntity<Void> viewStatusChange(@RequestBody Complaint complaint) {
		ResponseEntity<Void> resp = null;

		complaint.setDateOfApproval(LocalDate.now());
		Complaint c = complaintServ.updateComplaint(complaint);
		if (c == null)
			resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		else
			resp = new ResponseEntity<>(HttpStatus.OK);

		return resp;
	}

	@PutMapping("/Complaints/changeActionStatus")
	public ResponseEntity<Void> actionStatusChange(@RequestBody Complaint complaint) {
		ResponseEntity<Void> resp = null;

		Complaint c = complaintServ.updateComplaint(complaint);
		if (c == null)
			resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		else
			resp = new ResponseEntity<>(HttpStatus.OK);

		return resp;
	}
	
	@PutMapping("/changeTowerStatus")
	public ResponseEntity<Void> towerStatusChange(@RequestBody Tower tower) {
		ResponseEntity<Void> resp = null;

		Tower t = towerServ.changeTowerStatus(tower);
		if (t == null)
			resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		else
			resp = new ResponseEntity<>(HttpStatus.OK);

		return resp;
	}
	
	//CSA api's
	
	@GetMapping("/csa/{id}")
	public ResponseEntity<CSA> getCsaById(@PathVariable("id") int csaId) {
		ResponseEntity<CSA> resp;
		CSA csa = csaServ.getCsaByCsaId(csaId);

		if (csa == null) {
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			resp = new ResponseEntity<>(csa, HttpStatus.OK);
		}
		return resp;
	}
	
	@GetMapping("/username/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
		ResponseEntity<User> resp;
		
		User user = userServ.findByUsername(username);
		if (user == null) {
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			resp = new ResponseEntity<>(user, HttpStatus.OK);
		}
		return resp;
	}
	
	//to get all the customer support agents
	@GetMapping("/getAllCsa")
	public ResponseEntity<List<CSA>> getAllCsa() {
		return new ResponseEntity<>(csaServ.fetchAllCsa(), HttpStatus.OK);
	}
	
	

		@PostMapping("/reset/{email}")
		public ResponseEntity<Void> forgotPassword(@PathVariable("email") String emailSearching,@RequestBody String otp
				) {
			ResponseEntity<Void> resp = null;

			Admin admin = adminServ.findByEmail(emailSearching);
			CSA csa=csaServ.findByEmail(emailSearching);
			
			if(admin!=null) {
				if (admin.getEmail().equals(emailSearching)) {
					sendMail.setOtp(otp);
					sendMail.setTo(emailSearching);
					sendMail.setName(admin.getName());

					boolean isSent=sendMail.send();
					if (isSent)
						resp = new ResponseEntity<>(HttpStatus.OK);
					else
						resp= new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			} else if(csa!=null){
				if (csa.getEmail().equals(emailSearching)) {
					sendMail.setOtp(otp);
					sendMail.setTo(emailSearching);
					sendMail.setName(csa.getName());
					
					boolean isSent=sendMail.send();
					if (isSent)
						
						resp = new ResponseEntity<>(HttpStatus.OK);
						
					else
						resp= new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			} else{
				resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			//System.out.println(resp);
			return resp;
		}

	@PostMapping("/changepwd/{username}")
	public ResponseEntity<User> changePassword(@PathVariable("username") String username,
			@RequestBody String password) {

		ResponseEntity<User> resp = null;

		User updatedUser = null;
		User user = userServ.findByUsername(username);
		System.out.println("***************" + user.getPassword() + "*****************" + user.getUsername()+"##################"+password);
		if (user != null) {
			user.setPassword(bcryptEncoder.encode(password));
			updatedUser = userServ.updatePassword(user);
		}

		if (updatedUser != null) {
			resp = new ResponseEntity<User>(updatedUser, HttpStatus.OK);
		} else {
			resp = new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return resp;
	};

}
