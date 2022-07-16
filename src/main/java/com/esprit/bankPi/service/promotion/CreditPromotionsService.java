package com.esprit.bankPi.service.promotion;

import com.esprit.bankPi.model.promotion.CreditPromotions;
import com.esprit.bankPi.repository.promotion.CreditPromotionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CreditPromotionsService {
    @Autowired
    private CreditPromotionsRepository creditPromotionsRepository;

    public CreditPromotions addCreditPromotions(CreditPromotions creditPromotions) {
        return creditPromotionsRepository.save(creditPromotions);
    }

    public CreditPromotions updateCreditPromotions(CreditPromotions creditPromotions) {
        if (this.getCreditPromotionsById(creditPromotions.getId()) != null) {
            creditPromotionsRepository.save(creditPromotions);
        }
        return this.getCreditPromotionsById(creditPromotions.getId());
    }

    public CreditPromotions getCreditPromotionsById(Long creditPromotionsId) {
        Optional<CreditPromotions> creditPromotionsOptional = creditPromotionsRepository.findById(creditPromotionsId);
        return creditPromotionsOptional.orElse(null);
    }

    @Transactional
    public void deleteCreditPromotionsById(Long creditPromotionsId) {
        creditPromotionsRepository.delete(this.getCreditPromotionsById(creditPromotionsId));
    }

    public List<CreditPromotions> getAllCreditPromotions() {

        List<CreditPromotions> creditPromotionsList = (List<CreditPromotions>) creditPromotionsRepository.findAll();

        if (creditPromotionsList.size() > 0) {
            return creditPromotionsList;
        } else {
            return new ArrayList<CreditPromotions>();
        }
    }
}
