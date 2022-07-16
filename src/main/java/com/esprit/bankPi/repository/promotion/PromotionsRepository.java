package com.esprit.bankPi.repository.promotion;


import com.esprit.bankPi.model.promotion.Promotions;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PromotionsRepository extends CrudRepository<Promotions, Long> {

    List<Promotions> findAll();
}
