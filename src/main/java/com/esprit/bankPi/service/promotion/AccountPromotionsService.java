package com.esprit.bankPi.service.promotion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.esprit.bankPi.model.promotion.AccountPromotions;
import com.esprit.bankPi.repository.promotion.AccountPromotionsRespository;
//import com.esprit.bankPi.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountPromotionsService {
//    private static final Logger LOG = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountPromotionsRespository accountPromotionsRespository;

    public AccountPromotions addAccountPromotions(AccountPromotions accountPromotions) {
//        LOG.debug("Add Account Promotion :");
        return accountPromotionsRespository.save(accountPromotions);
    }

    public AccountPromotions updateAccountPromotions(AccountPromotions accountPromotions) {
//        LOG.debug("Update Account Promotion :");
        if (this.getAccountPromotionsById(accountPromotions.getId()) != null) {
            accountPromotionsRespository.save(accountPromotions);
        }
        return this.getAccountPromotionsById(accountPromotions.getId());
    }

    public AccountPromotions getAccountPromotionsById(Long accountPromotionsId) {
//        LOG.debug("Find Account Promotion :");
        Optional<AccountPromotions> accountPromotionsOptional = accountPromotionsRespository.findById(accountPromotionsId);
        return accountPromotionsOptional.orElse(null);
    }

    @Transactional
    public void deleteAccountPromotionsById(Long accountPromotionsId) {
//        LOG.debug("Delte Account Promotion :");
        accountPromotionsRespository.delete(this.getAccountPromotionsById(accountPromotionsId));
    }

    public List<AccountPromotions> getAllAccountPromotions() {
//        LOG.debug("Find All Account Promotions :");
        List<AccountPromotions> accountPromotionsList = (List<AccountPromotions>) accountPromotionsRespository.findAll();

        if (accountPromotionsList.size() > 0) {
            return accountPromotionsList;
        } else {
            return new ArrayList<AccountPromotions>();
        }
    }
}
