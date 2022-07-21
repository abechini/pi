package com.esprit.bankPi.repository.credit;
import com.esprit.bankPi.model.credit.Credit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CreditRepository extends CrudRepository<Credit, Long>, PagingAndSortingRepository<Credit, Long> {


}