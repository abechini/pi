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

//	@Query("SELECT t FROM transfert_pojo WHERE data_compte.compte.id=:idCompte")
//	List<TransfertPojo> findByCompte(@Param("idCompte") Long idCompte);
	
}
