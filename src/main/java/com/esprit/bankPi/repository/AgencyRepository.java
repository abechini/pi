package com.esprit.bankPi.repository;

import com.esprit.bankPi.data.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyRepository  extends JpaRepository<Agency,Long> {
    public void deleteByName(String name);
    public Agency save(Agency agency) ;
    public void findAllByAgent(String nameAgent) ;

}
