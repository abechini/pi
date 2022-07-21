package com.esprit.bankPi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esprit.bankPi.data.Complaint;
import com.esprit.bankPi.model.Reclamation;


@Repository
public interface ReclamationRepository extends JpaRepository<Reclamation,Long>{

}
