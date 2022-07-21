package com.esprit.bankPi.resources;

import com.esprit.bankPi.repository.AgencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgencyServiceImpl implements IAgencyService {
    @Autowired
    AgencyRepository  agencyRepository ;
}
