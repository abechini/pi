package com.esprit.bankPi.repository;


import com.esprit.bankPi.data.Client;
import com.esprit.bankPi.data.Complaint;
import com.esprit.bankPi.enums.ComplaintState;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint,Long> {
	@SuppressWarnings("unchecked")
	public  Complaint save(Complaint complaint) ;
	public List<Complaint> findByClient(Client client);
	public  Complaint save(ComplaintState state) ;
	public List<Complaint> findComplaintsByCurrentState(ComplaintState state) ;
	
	
}
