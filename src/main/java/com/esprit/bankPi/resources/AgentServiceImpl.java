package com.esprit.bankPi.resources;

import com.esprit.bankPi.data.Agent;
import com.esprit.bankPi.repository.AgentRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentServiceImpl implements  IAgentService{
    @Autowired
    AgentRepository  agentRepository ;

	@Override
	public Agent addAgent(Agent agent) {
		
	 return	agentRepository.save(agent) ;
		
	}
	
	@Override
	public List<Agent> findAgentsByAgency(String agencyName) {
		return agentRepository.findAllByAgencyName(agencyName);
	}
	@Override
	public Agent findAgentById(Long id) {
		return agentRepository.findAgentById(id);
	}

	
}
