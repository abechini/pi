package com.esprit.bankPi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.esprit.bankPi.data.CheckBook;

@Repository
public interface CheckBookRepository extends CrudRepository<CheckBook, Long>{

}
