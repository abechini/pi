package com.esprit.bankPi.controller.promotion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.esprit.bankPi.model.promotion.CreditPromotions;
import com.esprit.bankPi.service.promotion.CreditPromotionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/creditpromotions")
public class CreditPromotionsController {
    private static final Logger LOG = LoggerFactory.getLogger(CreditPromotionsController.class);

    @Autowired
    CreditPromotionsService creditPromotionsService;

    @PostMapping("/addCreditPromotions")
    @ResponseBody
    public CreditPromotions addCreditPromotions(@RequestBody CreditPromotions creditPromotions) {
        creditPromotionsService.addCreditPromotions(creditPromotions);
        return creditPromotions;
    }

    @PutMapping("/updateCreditPromotions/{id}")
    public CreditPromotions updateCreditPromotions(@RequestBody CreditPromotions creditPromotions, @PathVariable long id) {
        creditPromotions = creditPromotionsService.updateCreditPromotions(creditPromotions);
        return creditPromotions;
    }

    @GetMapping("/getCreditPromotionsById/{idCreditPromotions}")
    @ResponseBody
    public CreditPromotions getCreditPromotionsById(@PathVariable("idCreditPromotions") Long CreditPromotionsid) {

        return creditPromotionsService.getCreditPromotionsById(CreditPromotionsid);
    }

    @DeleteMapping("/deleteCreditPromotionsById/{idCreditPromotions}")
    @ResponseBody
    public void deleteCreditPromotionsById(@PathVariable("idCreditPromotions") Long CreditPromotionsid) {
        creditPromotionsService.deleteCreditPromotionsById(CreditPromotionsid);
    }

    @GetMapping("/getCreditPromotions")
    @ResponseBody
    public List<CreditPromotions> getAllCreditPromotions() {

        return creditPromotionsService.getAllCreditPromotions();
    }
}
