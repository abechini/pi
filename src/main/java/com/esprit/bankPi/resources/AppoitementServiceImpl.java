package com.esprit.bankPi.resources;

import com.esprit.bankPi.repository.AppoitementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppoitementServiceImpl implements IAppoitementService{
    @Autowired
    AppoitementRepository  appoitementRepository ;
}
