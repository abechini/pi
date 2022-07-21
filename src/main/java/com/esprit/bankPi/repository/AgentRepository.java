package com.esprit.bankPi.repository;

import com.esprit.bankPi.data.Agent;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
	public Agent save(Agent agent);
	public List<Agent> findAllByAgencyName(String agencyName) ;
	public Agent findAgentById(Long id);

}
