package com.esprit.bankPi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.esprit.bankPi.data.BankCarte;

@Repository
public interface BankCarteRepository extends CrudRepository<BankCarte, Long>{

}
