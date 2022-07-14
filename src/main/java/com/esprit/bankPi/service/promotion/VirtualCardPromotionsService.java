package com.esprit.bankPi.service.promotion;

import com.esprit.bankPi.model.promotion.VirtualCardPromotions;
import com.esprit.bankPi.repository.promotion.VirtualCardPromotionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VirtualCardPromotionsService {
    @Autowired
    VirtualCardPromotionsRepository virtualCardPromotionsRepository;

    public VirtualCardPromotions addVirtualCardPromotions(VirtualCardPromotions virtualCardPromotions) {
        return virtualCardPromotionsRepository.save(virtualCardPromotions);
    }

    public VirtualCardPromotions updateVirtualCardPromotions(VirtualCardPromotions virtualCardPromotions) {
        if (this.getVirtualCardPromotionsById(virtualCardPromotions.getId()) != null) {
            virtualCardPromotionsRepository.save(virtualCardPromotions);
        }
        return this.getVirtualCardPromotionsById(virtualCardPromotions.getId());
    }

    public VirtualCardPromotions getVirtualCardPromotionsById(Long virtualCardPromotionsId) {
        Optional<VirtualCardPromotions> virtualCardPromotionsOptional = virtualCardPromotionsRepository.findById(virtualCardPromotionsId);
        return virtualCardPromotionsOptional.orElse(null);
    }

    @Transactional
    public void deleteVirtualCardPromotionsById(Long virtualCardPromotionsId) {
        virtualCardPromotionsRepository.delete(this.getVirtualCardPromotionsById(virtualCardPromotionsId));
    }

    public List<VirtualCardPromotions> getAllVirtualCardPromotions() {

        List<VirtualCardPromotions> virtualCardPromotionsList = (List<VirtualCardPromotions>) virtualCardPromotionsRepository.findAll();

        if (virtualCardPromotionsList.size() > 0) {
            return virtualCardPromotionsList;
        } else {
            return new ArrayList<VirtualCardPromotions>();
        }
    }
}
