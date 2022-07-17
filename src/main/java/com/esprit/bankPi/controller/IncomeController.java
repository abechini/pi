package com.esprit.bankPi.controller;

import java.time.YearMonth;
import java.util.List;

import org.springframework.stereotype.Controller;

import com.esprit.bankPi.data.Compte;
import com.esprit.bankPi.data.Income;

@Controller(value = "incomeController")
public class IncomeController {
	
public static double getTotalIncome(Compte compte, YearMonth toDate) {
	Double total = 0d;
	List<Income> incomes = compte.getIncomes();
	for(Income in : incomes) {
		if(in.getIncomeEndDate().isAfter(toDate)) {
			total = total + in.getIncomeAmount();
		}
	}
	return total;
}
}
