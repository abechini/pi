package com.esprit.bankPi.repository.credit;

import com.esprit.bankPi.model.credit.Credit;
import com.esprit.bankPi.model.credit.Payment;
import com.esprit.bankPi.enums.CreditStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;
public interface PaymentRepository extends CrudRepository<Payment, Long> {


    @Query("SELECT p FROM Payment p WHERE p.credit = ?1 and p.paymentStatus = ?2 ORDER BY p.paymentDueDate")
    Set<Payment> findAllNotPayedPayments(Credit creditId, CreditStatus status);
}