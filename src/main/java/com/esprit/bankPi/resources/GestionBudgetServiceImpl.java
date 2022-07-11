package com.esprit.bankPi.resources;

import java.time.YearMonth;
import java.util.Date;
import java.util.Map;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.bankPi.controller.GestionBudgetController;

@RestController
@RequestMapping("/budget")
public class GestionBudgetServiceImpl {

	@Autowired
	GestionBudgetController gestionBudget;

	@GetMapping(path="/whenReachTarget", produces = "application/json")
	public YearMonth getWhenReachTarget(@QueryParam("idUser") Long idUser, @QueryParam("target") Float target,
			@QueryParam("numeroCompte") Long numeroCompte) throws Exception {
		return gestionBudget.getWhenReachTarget(idUser, target, numeroCompte);
	}

	@GetMapping(path="/howReachTarget", produces = "application/json")
	public Map<YearMonth, Float> getHowReachTarget(@QueryParam("idUser") Long idUser,
			@QueryParam("targetMoney") Float targetMoney, @QueryParam("targetDate") YearMonth targetDate,
			@QueryParam("numeroCompte") Long numeroCompte) {
		return gestionBudget.getHowReachTarget(idUser, targetMoney, targetDate, numeroCompte);
	}
}
