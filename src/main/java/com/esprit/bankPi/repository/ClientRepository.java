package com.esprit.bankPi.repository;

import com.esprit.bankPi.data.Agency;
import com.esprit.bankPi.data.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
}
