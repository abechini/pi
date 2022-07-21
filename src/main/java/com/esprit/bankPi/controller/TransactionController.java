package com.esprit.bankPi.controller;

import java.time.YearMonth;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.esprit.bankPi.data.Client;
import com.esprit.bankPi.data.DepositPojo;
import com.esprit.bankPi.data.WithdrowPojo;
import com.esprit.bankPi.repository.DepositRepository;
import com.esprit.bankPi.repository.WithdrowRepository;
import com.esprit.bankPi.resources.TransactionServiceImpl;

@Controller(value = "TransactionController")
@RequestMapping("/api/transactions")
public class TransactionController {
	@Autowired
	TransactionServiceImpl transactionServiceImpl;

	@Autowired
	static DepositRepository depositRepository;
	@Autowired
	static WithdrowRepository withdrowRepository;

	public static Map<YearMonth, Double> getSavings(Client client) {
		Map<YearMonth, Double> savings = new HashMap<YearMonth, Double>();
//		YearMonth yearMonth = YearMonth.now();
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

	@GetMapping(value = "/exportExcel/{idCompte}")
	public ResponseEntity exportExcel(@PathVariable long idCompte) {
		transactionServiceImpl.exportExtrait(idCompte);
		return ResponseEntity.status(HttpStatus.OK).body("transactions exported successfully");
	}

}
