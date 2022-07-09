package com.esprit.bankPi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.esprit.bankPi.data.TransfertPojo;

@Repository
public interface TransfertRepository extends CrudRepository<TransfertPojo, Integer>{

}
