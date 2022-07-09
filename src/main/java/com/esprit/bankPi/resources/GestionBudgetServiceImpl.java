package com.esprit.bankPi.resources;

import java.time.YearMonth;
import java.util.Date;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.esprit.bankPi.controller.GestionBudgetController;

@Path("/budget")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class GestionBudgetServiceImpl implements IGestionBudgetService {

	@Autowired
	GestionBudgetController gestionBudget;

	@GET
	@Path("/WhenReachTarget")
	public Date getWhenReachTarget(@QueryParam("idUser") Long idUser, @QueryParam("target") Float target,
			@QueryParam("numeroCompte") Long numeroCompte) throws Exception {
		return gestionBudget.getWhenReachTarget(idUser, target, numeroCompte);
	}

	@GET
	@Path("/HowReachTarget")
	public Map<YearMonth, Float> getHowReachTarget(@QueryParam("idUser") Long idUser,
			@QueryParam("targetMoney") Float targetMoney, @QueryParam("targetDate") YearMonth targetDate,
			@QueryParam("numeroCompte") Long numeroCompte) {
		return gestionBudget.getHowReachTarget(idUser, targetMoney, targetDate, numeroCompte);
	}
}
