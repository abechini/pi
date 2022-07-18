package com.esprit.bankPi.resources;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.esprit.bankPi.data.Agency;
import com.esprit.bankPi.data.Complaint;
import com.esprit.bankPi.enums.ComplaintState;

@Service
public interface IComplaintService {
	
 public List<Complaint> findComplaintByState(ComplaintState state) ;
 public List<Complaint> findComplaintByPriority(String priority) ;
 public Agency worstAgencyOfthePriod (Date StartDate, Date endDate) ;
 public Complaint addComplaint(Complaint complaint) ;
 public Complaint changeState(ComplaintState state) ;
 public void changePriority(String priority) ;
 public Complaint updateComplaint(Complaint complaint);

 
 


}
