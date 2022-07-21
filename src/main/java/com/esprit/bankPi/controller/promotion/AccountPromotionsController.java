package com.esprit.bankPi.controller.promotion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.esprit.bankPi.model.promotion.AccountPromotions;
import com.esprit.bankPi.service.promotion.AccountPromotionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/accountpromotions")
public class AccountPromotionsController {
    private static final Logger LOG = LoggerFactory.getLogger(AccountPromotionsController.class);

    @Autowired
    AccountPromotionsService accountPromotionsService;

    // @PreAuthorize(Constants.HAS_ROLE_ADMIN)
    @PostMapping("/addAccountPromotions")
    @ResponseBody
    public AccountPromotions addAccountPromotions(@RequestBody AccountPromotions accountPromotions) {
        accountPromotionsService.addAccountPromotions(accountPromotions);
        return accountPromotions;
    }

    //  @PreAuthorize(Constants.HAS_ROLE_ADMIN)
    @PutMapping("/updateAccountPromotions/{id}")
    public AccountPromotions updateAccountPromotions(@RequestBody AccountPromotions accountPromotions, @PathVariable long id) {
        accountPromotions = accountPromotionsService.updateAccountPromotions(accountPromotions);
        return accountPromotions;
    }

    //  @PreAuthorize(Constants.HAS_ROLE_USER)
    @GetMapping("/getAccountPromotionsById/{idAccountPromotions}")
    @ResponseBody
    public AccountPromotions getAccountPromotionsById(@PathVariable("idAccountPromotions") Long AccountPromotionsid) {

        return accountPromotionsService.getAccountPromotionsById(AccountPromotionsid);
    }

    //  @PreAuthorize(Constants.HAS_ROLE_ADMIN)
    @DeleteMapping("/deleteAccountPromotionsById/{idAccountPromotions}")
    @ResponseBody
    public void deleteAccountPromotionsById(@PathVariable("idAccountPromotions") Long AccountPromotionsid) {
        accountPromotionsService.deleteAccountPromotionsById(AccountPromotionsid);
    }

    //  @PreAuthorize(Constants.HAS_ROLE_USER)
    @GetMapping("/getAccountPromotions")
    @ResponseBody
    public List<AccountPromotions> getAllAccountPromotions() {

        return accountPromotionsService.getAllAccountPromotions();
    }


}
