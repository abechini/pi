package com.esprit.bankPi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.esprit.bankPi.data.Compte;


@Repository
public interface CompteRepository extends CrudRepository<Compte, Long>{

}
