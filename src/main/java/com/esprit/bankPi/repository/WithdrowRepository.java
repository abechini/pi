package com.esprit.bankPi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esprit.bankPi.data.WithdrowPojo;
import com.esprit.bankPi.model.Withdraw;

@Repository
public interface WithdrowRepository extends CrudRepository<WithdrowPojo, Integer>{
	
//	@Query("SELECT w FROM withdrow WHERE compte.id = :idCompte")
//	List<WithdrowPojo> findByCompte(@Param("idCompte") Long idCompte);

}
