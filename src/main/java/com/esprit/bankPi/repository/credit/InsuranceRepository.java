package com.esprit.bankPi.repository.credit;
import com.esprit.bankPi.enums.CreditRequestStatus;
import com.esprit.bankPi.model.credit.CreditRequest;
import com.esprit.bankPi.model.credit.Insurance;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface InsuranceRepository extends CrudRepository<Insurance, Long> {

	
}
