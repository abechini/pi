package com.esprit.bankPi.repository.credit;


import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.esprit.bankPi.enums.CreditRequestStatus;
import com.esprit.bankPi.model.credit.CreditRequest;  
public interface CreditRequestRepository extends CrudRepository<CreditRequest, Long> {

    @Query("SELECT p FROM CreditRequest p WHERE p.creditRequestStatus = ?1 ORDER BY p.creationDate DESC ")
    Set<CreditRequest> findAllCreditRequestWithStatus(CreditRequestStatus status);
}
