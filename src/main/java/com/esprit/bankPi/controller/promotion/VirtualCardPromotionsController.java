package com.esprit.bankPi.controller.promotion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import com.esprit.bankPi.model.account.Account;
//import com.esprit.bankPi.model.account.DecisionsBatch;
//import com.esprit.bankPi.model.credit.Credit;
import com.esprit.bankPi.model.promotion.VirtualCardPromotions;
import com.esprit.bankPi.service.promotion.VirtualCardPromotionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/vitualcardpromotions")
public class VirtualCardPromotionsController {
    private static final Logger LOG = LoggerFactory.getLogger(VirtualCardPromotionsController.class);
    @Autowired
    VirtualCardPromotionsService virtualCardPromotionsService;

    public static int calculateAge(Date dob, Date date) {
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        int d1 = Integer.parseInt(formatter.format(dob));
        int d2 = Integer.parseInt(formatter.format(date));
        int age = (d2 - d1) / 10000;
        return age;


    }

    @PostMapping("/addVitualCardPromotions")
    @ResponseBody
    public VirtualCardPromotions addVirtualCardPromotions(@RequestBody VirtualCardPromotions virtualCardPromotions) {
        virtualCardPromotionsService.addVirtualCardPromotions(virtualCardPromotions);
        return virtualCardPromotions;
    }

    @PutMapping("/updateVitualCardPromotions/{id}")
    public VirtualCardPromotions updateVirtualCardPromotions(@RequestBody VirtualCardPromotions virtualCardPromotions, @PathVariable long id) {
        virtualCardPromotions = virtualCardPromotionsService.updateVirtualCardPromotions(virtualCardPromotions);
        return virtualCardPromotions;
    }

    @GetMapping("/getVitualCardPromotionsById/{idVitualCardPromotions}")
    @ResponseBody
    public VirtualCardPromotions getVirtualCardPromotionsById(@PathVariable("idVirtualCardPromotions") Long virtualCardPromotionsid) {

        return virtualCardPromotionsService.getVirtualCardPromotionsById(virtualCardPromotionsid);
    }

    @DeleteMapping("/deleteVitualCardPromotionsById/{idVitualCardPromotions}")
    @ResponseBody
    public void deleteVirtualCardPromotionsById(@PathVariable("idVirtualCardPromotions") Long VirtualCardPromotionsid) {
        virtualCardPromotionsService.deleteVirtualCardPromotionsById(VirtualCardPromotionsid);
    }

    @GetMapping("/getVitualCardPromotions")
    @ResponseBody
    public List<VirtualCardPromotions> getAllVirtualCardPromotions() {

        return virtualCardPromotionsService.getAllVirtualCardPromotions();
    }

    /*
        public ResponseEntity<?> cardRule(@PathVariable Account account ) {
                if (account.getOwner().hascreditcard) {
                account.setRating(account + getrating + 10)

            }
        }*/
    //Les clients sont admissibles à cette promotion
    //  s'ils ont entre 21 et 35 ans (inclus) et solde supérieur à 10 000 $ et un compte epargne
//    public void accountRule(Account account, DecisionsBatch decisionsBatch) {
//        if (calculateAge(account.getOwner().getUserProfile().getDob(), new Date()) >= 21 && calculateAge(account.getOwner().getUserProfile().getDob(), new Date()) <= 35 && account.getBalance().compareTo(new BigDecimal(10000)) == 1 && account.getAccountType().getName().equals("Checking")) {
//            decisionsBatch.setNewRating(100);
//        }
//    }

    //Les clients sont admissibles à cette promotion s'ils ont 65 ans et plus
    //et avoir un crédit de 50 ou moins, ou un solde supérieur à 5 000 $
//    public void loanRule(Account account, DecisionsBatch decisionsBatch, Credit credit) {
//        if (calculateAge(account.getOwner().getUserProfile().getDob(), new Date()) >= 65 && credit.getCreditAmount() == 50000 || account.getBalance().compareTo(new BigDecimal(5000)) == 1) {
//            decisionsBatch.setNewRating(500);
//        }
//    }
    // Les clients se qualifient pour cette rule s'ils ont été à la banque depuis au moins 5 ans
    // et ils ont une carte de crédit cette année et un nombre de transaction(debit) plus qu
   /* public void creditCardRule(Account account, DecisionsBatch decisionsBatch) {
        if (calculateAge( account.getOwner().getUserProfile().getDom(),new Date()) >= 5  && credit.getCreditAmount()==50000 && account.getAcountTransactionList().stream().count() > 10) {
            decisionsBatch.setNewRating(500);
        }
    }*/
}
