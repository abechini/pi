package com.esprit.bankPi.repository;

import com.esprit.bankPi.data.Agent;
import com.esprit.bankPi.data.Appoitement;
import com.esprit.bankPi.data.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppoitementRepository  extends JpaRepository<Appoitement,Long> {
    public List<Appoitement> findAppoitementByClient(Client client);
    public void deleteByAgent(Agent agent) ;
    public void deleteByClient(Client client) ;
}
