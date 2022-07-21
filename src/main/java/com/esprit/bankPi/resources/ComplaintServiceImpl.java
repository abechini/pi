package com.esprit.bankPi.resources;


import com.esprit.bankPi.data.Agency;
import com.esprit.bankPi.data.Complaint;
import com.esprit.bankPi.enums.ComplaintState;
import com.esprit.bankPi.repository.ClientRepository;
import com.esprit.bankPi.repository.ComplaintRepository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComplaintServiceImpl implements IComplaintService {
	@Autowired
	ComplaintRepository complaintRepository;

	@Override
	public List<Complaint> findComplaintByPriority(String priority) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Agency worstAgencyOfthePriod(Date StartDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Complaint addComplaint(Complaint complaint) {
		return complaintRepository.save(complaint);

	}

	@Override
	public Complaint updateComplaint(Complaint complaint) {
		return complaintRepository.save(complaint);

	}

	@Override
	public List<Complaint> findComplaintByState(ComplaintState state) {

		return complaintRepository.findComplaintsByCurrentState(state);
	}

	@Override
	public Complaint changeState(ComplaintState state) {
	/*	SendComplaintNotification sendComplaintNotification = new SendComplaintNotification();
		sendComplaintNotification.SendChangeStatMailNotification(state);*/
		return complaintRepository.save(state);
	}

	@Override
	public void changePriority(String priority) {
		// TODO Auto-generated method stub

	}

}
