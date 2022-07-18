package com.esprit.bankPi.simulator;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.esprit.bankPi.util.SystemMessages;


import org.springframework.stereotype.Service;


@Service

public class LoanSimulatorService {
    private static final Logger LOG = LoggerFactory.getLogger(LoanSimulatorService.class);
  

    public String calculateCreditMentuality(double loanAmount, double intrestRate, double months) {
        double t = intrestRate / 100;
        //Mensualité = [capital × (taux/12)]/[1 – (1 + (taux/12) – (12 × nombre d’années de remboursement))] 
        double t1 = loanAmount * t / 12;
   //   double t2 = (1 - (1 + (t /12) - months));
      double t2 = 1 - Math.pow(1 + t / 12, -months);
     
        return "  Monthly loan payment ="+(t1 / t2)+" DT" + "    ===>   The amount of interest added per month ="+(t1/ (1 - (1 + (t /12) - months))) +" DT"  ;
    }

    public String calculateCarCreditMentuality(double loanAmount, double months) {
        double intrestRate = 0;
        if (months < 36) {
            LOG.error("Car loan should be on 3 years or more");
       
         String message = SystemMessages.CAR_LOAN_MIN_YEARS;
        
        return message;
           
        } else if (months == 36) {
            intrestRate = 4.21;
        } else if (months <= 48 && months >= 37) {
            intrestRate = 4.31;
        } else if (months <= 60 && months >= 49) {
            intrestRate = 4.37;
        } else if (months <= 72 && months >= 61) {
            intrestRate = 4.45;
        } else if (months > 72) {
            LOG.error("Car loan couldn't be on more than 6 years ");
     
        String message = SystemMessages.CAR_LOAN_MAX_YEARS;
        
        return message;
        
        }
        double t = intrestRate / 100;
        double t1 = loanAmount * t / 12;
        double t2 = 1 - Math.pow(1 + t / 12, -months);
        return " Car Credit  Monthly payment="+(t1 / t2)+"  DT";
    }

    public String calculateHomeCreditMentuality(double loanAmount, double months) {
        double intrestRate = 0;
        if ((months / 12) < 15) {
            LOG.error("Home loan should be on more than 15 years ");
            
            String message = SystemMessages.HOME_LOAN_MIN_YEARS;
            
            return message;
          
        } else if ((months / 12) == 15) {
            intrestRate = 2.5;
        } else if ((months / 12) <= 30 && (months / 12) > 15) {
            intrestRate = 3.2;
           
        } else if ((months / 12) > 30) {
        	 LOG.error("Home loan couldn't be on more than 30 years");
 
        	String message = SystemMessages.HOME_LOAN_MAX_YEARS;
   
    return message;

        }
    	
        double t = intrestRate / 100;
        double t1 = loanAmount * t / 12;
        double t2 = 1 - Math.pow(1 + t / 12, -months);
        
        return "Home Credit Monthly payment="+(t1 / t2)+"  DT";
    }

}
