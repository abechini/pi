package com.esprit.bankPi.resources;

import java.util.List;

import org.springframework.stereotype.Service;

import com.esprit.bankPi.data.Agent;



@Service
public interface IAgentService {
	
	 public Agent addAgent(Agent agent) ;
	 public List<Agent> findAgentsByAgency(String agencyName) ;
	 public Agent findAgentById(Long id) ;
	 
}
