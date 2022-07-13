package com.esprit.bankPi.simulator;

import com.esprit.bankPi.simulator.LoanSimulator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface LoanSimulatorRepository  extends JpaRepository<LoanSimulator,Long>{

}
