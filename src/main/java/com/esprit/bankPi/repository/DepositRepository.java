package com.esprit.bankPi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esprit.bankPi.data.DepositPojo;

@Repository
public interface DepositRepository extends CrudRepository<DepositPojo, Integer> {

	@Query(value = "SELECT * FROM deposit_pojo d WHERE d.compte_numero_compte=:idCompte", nativeQuery = true)
	List<DepositPojo> findByCompte(@Param("idCompte") Long idCompte);

}
