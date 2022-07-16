package com.esprit.bankPi.service.promotion;

import com.esprit.bankPi.model.promotion.Promotions;
import com.esprit.bankPi.repository.promotion.PromotionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionsService {
    @Autowired
    private PromotionsRepository promotionsRepository;

    public List<Promotions> getAllPromotions() {

        return promotionsRepository.findAll();

    }
}
