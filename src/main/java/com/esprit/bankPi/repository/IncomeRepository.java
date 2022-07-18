package com.esprit.bankPi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.esprit.bankPi.data.Income;

@Repository
public interface IncomeRepository extends CrudRepository<Income, Long>{

}
