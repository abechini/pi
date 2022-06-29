package com.esprit.bankPi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.esprit.bankPi.data.DepositPojo;

@Repository
public interface DepositRepository extends CrudRepository<DepositPojo, Integer>{

}
