package com.esprit.bankPi.repository;

import com.esprit.bankPi.data.Agent;
import com.esprit.bankPi.data.Appoitement;
import com.esprit.bankPi.data.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppoitementRepository  extends JpaRepository<Appoitement,Long> {
    public List<Appoitement> findAppoitementByClient(Client client);
    public void deleteByAgent(Agent agent) ;
    public void deleteByClient(Client client) ;
    @Query("select a from Appoitement a where a.agent.id = :agentId and  a.start >=:dayStart and  a.start <=:dayEnd")
    List<Appoitement> findByProviderIdWithStartInPeroid(@Param("agentId") Long agentId, @Param("dayStart") LocalDateTime startPeroid, @Param("dayEnd") LocalDateTime endPeroid);
    @Query("select a from Appoitement a where a.start >=:dayStart and  a.start <=:dayEnd")
    List<Appoitement> findAllAppointmentWithStartInPeroid( @Param("dayStart") LocalDateTime startPeroid, @Param("dayEnd") LocalDateTime endPeroid);
  
}
