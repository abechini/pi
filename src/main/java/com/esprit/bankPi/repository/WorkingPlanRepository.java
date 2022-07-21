package com.esprit.bankPi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esprit.bankPi.data.WorkingPlan;

@Repository
public interface WorkingPlanRepository extends JpaRepository<WorkingPlan,Long>{
	
@SuppressWarnings("unchecked")
public WorkingPlan save(WorkingPlan workingPlan) ;
}
