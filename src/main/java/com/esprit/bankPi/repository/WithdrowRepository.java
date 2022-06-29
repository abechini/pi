package com.esprit.bankPi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.esprit.bankPi.data.WithdrowPojo;

@Repository
public interface WithdrowRepository extends CrudRepository<WithdrowPojo, Integer>{

}
