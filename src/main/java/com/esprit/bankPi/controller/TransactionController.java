package com.esprit.bankPi.controller;

import java.time.YearMonth;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;

import com.esprit.bankPi.data.Client;

@Controller(value = "TransactionController")
public class TransactionController {
	//you have to implement it
	// i want to have for each month/year how much the client saved money ( money recived - money used ) 
	//from first day of the month to last day of the same month
	//@author(aziz) :p
public static Map<YearMonth,Double> getSavings(Client client) {
	Map<YearMonth,Double> savings = new HashMap<YearMonth, Double>();
	YearMonth yearMonth = YearMonth.now();
	for(int i = 0; i<3; i++) {
		savings.put(yearMonth.minusMonths(i), Math.random()*10);
	}
	return savings;
	
}
}
