package com.esprit.bankPi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esprit.bankPi.data.DepositPojo;
import com.esprit.bankPi.model.Deposit;

@Repository
public interface DepositRepository extends CrudRepository<DepositPojo, Integer> {

//	@Query("SELECT d FROM data_Deposit WHERE compte.id=:idCompte")
//	List<DepositPojo> findByCompte(@Param("idCompte") Long idCompte);

}
