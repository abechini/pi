package com.esprit.bankPi.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.esprit.bankPi.data.Compte;

import java.util.List;

public interface AccountRepository extends CrudRepository<Compte, Long>, PagingAndSortingRepository<Compte, Long> {
 

    Page<Compte> findAll(Pageable pageable);

    List<Compte> findAll();

   
}