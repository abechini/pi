package com.esprit.bankPi.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.bankPi.repository.WorkingPlanRepository;

@Service
public class WorkingPlanImpl  implements IWorkingPlan{
	@Autowired
	WorkingPlanRepository workingPlanRepository;

	@Override
	public void deleteAll() {
		workingPlanRepository.deleteAll();
		
	}
	
	
}
