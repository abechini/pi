package com.esprit.bankPi.controller;

import java.util.List;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.bankPi.data.Complaint;
import com.esprit.bankPi.enums.ComplaintState;
import com.esprit.bankPi.resources.ComplaintServiceImpl;

@RestController
@RequestMapping("/complaint")
public class ComplaintController {

	@Autowired
	ComplaintServiceImpl complaintService;

	@PostMapping(path = "/addCompaint")
	public Complaint addComplaint(@RequestBody Complaint complaint) throws Exception {
		return complaintService.addComplaint(complaint);
	}

	@GetMapping(path = "/getAllComplainByState/{complaintState}", produces = "application/json")
	public List<Complaint> getComplaintByState(@PathVariable("complaintState") ComplaintState complaintState) {
		return complaintService.findComplaintByState(complaintState);
	}
	@PutMapping(path = "/updateCompaint")
	public Complaint updateComplaint(@RequestBody Complaint complaint) throws Exception {
		return complaintService.addComplaint(complaint);
	}
	@PutMapping(path = "/updateComplaintState")
	public Complaint updateComplaintState(@QueryParam("complaintState") ComplaintState complainState, @RequestBody Complaint complain ) throws Exception {
		return complaintService.changeState(complainState);
	}
	
	
	
	
	

}
