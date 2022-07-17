package com.esprit.bankPi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esprit.bankPi.data.Compte;


@Repository
public interface CompteRepository extends CrudRepository<Compte, Long>{

//	@Query("SELECT c FROM data_Compte WHERE data_Compte.name:=clientName")
//	Optional<Compte> findByClientName(@Param("clientName") String clientName);
	
}
