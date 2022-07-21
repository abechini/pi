package com.esprit.bankPi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.esprit.bankPi.data.Check;

@Repository
public interface CheckRepository extends CrudRepository<Check, Long>{

}
