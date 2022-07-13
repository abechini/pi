package com.esprit.bankPi.repository.credit;


import com.esprit.bankPi.model.credit.Credit;
import com.esprit.bankPi.model.credit.CreditRequest;
import com.esprit.bankPi.model.credit.Payment;
import com.esprit.bankPi.enums.CreditRequestStatus;
import com.esprit.bankPi.enums.CreditStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;  
public interface CreditRequestRepository extends CrudRepository<CreditRequest, Long> {

    @Query("SELECT p FROM CreditRequest p WHERE p.creditRequestStatus = ?1 ORDER BY p.creationDate DESC ")
    Set<CreditRequest> findAllCreditRequestWithStatus(CreditRequestStatus status);
}
