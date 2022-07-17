package com.esprit.bankPi.controller.promotion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.esprit.bankPi.model.promotion.Promotions;
import com.esprit.bankPi.service.promotion.PromotionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/promotions")
public class PromotionsController {
    private static final Logger LOG = LoggerFactory.getLogger(PromotionsController.class);
    @Autowired
    PromotionsService promotionsService;

    @GetMapping("/getPromotions")
    @ResponseBody
    public List<Promotions> getAllCreditPromotions() {

        return promotionsService.getAllPromotions();
    }
}
