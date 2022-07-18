package com.esprit.bankPi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esprit.bankPi.data.TransfertPojo;
import com.esprit.bankPi.model.Transfert;

@Repository
public interface TransfertRepository extends CrudRepository<TransfertPojo, Integer>{

	@Query(value = "SELECT * FROM trasfert_pojo t WHERE t.compte_numero_compte=:idCompte", nativeQuery = true)
	List<TransfertPojo> findByCompte(@Param("idCompte") Long idCompte);
	
}
