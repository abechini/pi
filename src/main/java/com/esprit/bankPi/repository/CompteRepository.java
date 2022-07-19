package com.esprit.bankPi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esprit.bankPi.data.Compte;


@Repository
public interface CompteRepository extends CrudRepository<Compte, Long>{


	@Query(value = "SELECT * FROM data_Compte c WHERE c.name=:clientName", nativeQuery = true)
	Optional<Compte> findByClientName(@Param("clientName") String clientName);
	
}
