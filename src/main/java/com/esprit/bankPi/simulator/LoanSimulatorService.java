package com.esprit.bankPi.simulator;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.esprit.bankPi.util.SystemMessages;

import org.springframework.stereotype.Service;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



@Service
public class LoanSimulatorService {
    private static final Logger LOG = LoggerFactory.getLogger(LoanSimulatorService.class);

	public Double getTMM(){
		
	  Document doc = null;
	try {
		doc = Jsoup.connect("https://www.bct.gov.tn/bct/siteprod/tableau_statistique_a.jsp?params=PL203105&la=AR").get();
	} catch (IOException e) {
		e.printStackTrace();
	}
	    Elements tableRows = doc.select(".bct-table-fixed table tr");
	    Double lastValue = 0d;
	    for (Element tableRow : tableRows) {
	        String rowData = tableRow.text();
	        if (rowData.split(" ").length == 7){
	            lastValue = Double.parseDouble(rowData.split(" ")[6]);
	        }
	    }
	    return lastValue;
		
	}


    public String calculateCreditMentuality(double loanAmount, double months) {
    	//intrestRate
    	double intrestRate= getTMM()+ (getTMM()*15/100 );
    	 if (months > 36) {
          String message = SystemMessages.CONSOMATION_LOAN_MAX_YEARS;
         
         return message;
        
         }else {
    
        double t = intrestRate / 100;
        double t1 = (loanAmount * t) / 12;
        //mensualité formule mathématique = [(M*t)/12] / [1-(1+(t/12))^-n].
      double t2 = 1 - Math.pow(1 + t / 12, -months);
     
        return "  Monthly loan payment ="+(t1 / t2)+" DT" +"                  => TMM today = "+ getTMM()+"    Interest rate = "+ intrestRate;
    }}
    
//CAR
    public String calculateCarCreditMentuality(double loanAmount, double months) {
    	double intrestRate= getTMM()+ (getTMM()*15/100 );
    	 double taux = 0;
        if (months < 36) {
            LOG.error("Car loan should be on 3 years or more");
       
         String message = SystemMessages.CAR_LOAN_MIN_YEARS;
        
        return message;
       
        } else if (months == 36) {
            double intrestRate1 = intrestRate + (intrestRate*20)/100;
            taux=intrestRate1;
          
        } else if (months <= 48 && months >= 37) {
            double intrestRate2 = intrestRate + (intrestRate*22)/100;
            taux=intrestRate2;
        } else if (months <= 60 && months >= 49) {
            double intrestRate3 = intrestRate +(intrestRate*25)/100;
            taux=intrestRate3;
        } else if (months <= 72 && months >= 61) {
            double intrestRate4 = intrestRate + (intrestRate*27)/100;
            taux=intrestRate4;
        } else if (months > 72) {
            LOG.error("Car loan couldn't be on more than 6 years ");
     
        String message = SystemMessages.CAR_LOAN_MAX_YEARS;
        
        return message;
        
        }
        double t = taux / 100;
        double t1 = loanAmount * t / 12;
        double t2 = 1 - Math.pow(1 + t / 12, -months);
        return " Car Credit  Monthly payment="+(t1 / t2)+"  DT" +"                  => TMM today = "+ getTMM()+"    Interest rate = "+ taux;
    }

    public String calculateHomeCreditMentuality(double loanAmount, double months) {
    	double intrestRate= getTMM()+ (getTMM()*15/100 );
    	 double taux = 0;
        if ((months / 12) < 15) {
            LOG.error("Home loan should be on more than 15 years ");
            
            String message = SystemMessages.HOME_LOAN_MIN_YEARS;
            
            return message;
          
        } else if ((months / 12) == 15) {
        	
            double intrestRate1 = intrestRate + (intrestRate*24)/100;
            taux=intrestRate1;
        } else if ((months / 12) <= 30 && (months / 12) > 15) {
            double intrestRate2 = intrestRate + (intrestRate*34)/100;
            taux=intrestRate2;
           
        } else if ((months / 12) > 30) {
        	 LOG.error("Home loan couldn't be on more than 30 years");
 
        	String message = SystemMessages.HOME_LOAN_MAX_YEARS;
   
    return message;

        }
    	
        double t = intrestRate / 100;
        double t1 = loanAmount * t / 12;
        double t2 = 1 - Math.pow(1 + t / 12, -months);
        
        return "Home Credit Monthly payment="+(t1 / t2)+"  DT" +"                  => TMM today = "+ getTMM()+"    Interest rate = "+ taux;
    }

}
