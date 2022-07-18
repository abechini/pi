package com.esprit.bankPi.controller;

import java.time.YearMonth;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.esprit.bankPi.data.Client;
import com.esprit.bankPi.data.DepositPojo;
import com.esprit.bankPi.data.WithdrowPojo;
import com.esprit.bankPi.repository.DepositRepository;
import com.esprit.bankPi.repository.WithdrowRepository;

@Controller(value = "TransactionController")
public class TransactionController {

	@Autowired
	static DepositRepository depositRepository;
	@Autowired
	static WithdrowRepository withdrowRepository;

	// you have to implement it
	// i want to have for each month/year how much the client saved money ( money
	// recived - money used )
	// from first day of the month to last day of the same month
	// @author(aziz) :p

//	public static Map<YearMonth, Double> getSavings(Client client) {
//		Map<YearMonth, Double> savings = new HashMap<YearMonth, Double>();
//		YearMonth yearMonth = YearMonth.now();
//		for (int i = 0; i < 3; i++) {
//			savings.put(yearMonth.minusMonths(i), Math.random() * 10);
//		}
//		return savings;
//	}

	public static Map<YearMonth, Double> getSavings(Client client) {
		Map<YearMonth, Double> savings = new HashMap<YearMonth, Double>();
		YearMonth yearMonth = YearMonth.now();
		Long idCompte = client.getCompteList().get(0).getNumeroCompte();

		List<DepositPojo> listDeposit = depositRepository.findByCompte(idCompte);

		List<WithdrowPojo> listWithdraw = withdrowRepository.findByCompte(idCompte);

		int i = 0, j = 0;

		while (i > 0 && j > 0) {
			DepositPojo tempDepo = listDeposit.get(i);
			WithdrowPojo tempWithdraw = listWithdraw.get(j);

			YearMonth yearMonthDeposit = YearMonth.from(new Date(tempDepo.getTransaction_date()).toInstant());

			YearMonth yearMonthwithdraw = YearMonth.from(new Date(tempWithdraw.getTransaction_date()).toInstant());
			YearMonth curent;

			if (yearMonthDeposit.equals(yearMonthwithdraw)) {
				curent = yearMonthDeposit;
			} else if (yearMonthDeposit.getYear() == yearMonthwithdraw.getYear()
					&& yearMonthDeposit.getMonthValue() >= yearMonthwithdraw.getMonthValue()) {
				curent = yearMonthwithdraw;
			} else {
				curent = yearMonthDeposit;
			}

			double mounthlyDepo = 0;
			double mounthlyWithdraw = 0;
			double rslt = 0;

			if (yearMonthDeposit.equals(curent)) {
				mounthlyDepo = +tempDepo.getAmount_in_number();
			} else {
				rslt = +mounthlyDepo;
			}
			if (yearMonthwithdraw.equals(curent)) {
				mounthlyWithdraw = +tempWithdraw.getAmount_in_number();
			} else {
				rslt = -mounthlyWithdraw;
			}

			if (!yearMonthDeposit.equals(curent) && !yearMonthwithdraw.equals(curent)) {
				if (i < listDeposit.size()) {
					i++;
				}
				if (j < listWithdraw.size()) {
					j++;
				}
				savings.put(curent, rslt);
			}

		}

		return savings;
	}
}
