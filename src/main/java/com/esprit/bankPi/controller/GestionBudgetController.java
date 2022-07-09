package com.esprit.bankPi.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.esprit.bankPi.data.Client;
import com.esprit.bankPi.ml.ClientInstance;
import com.esprit.bankPi.repository.ClientRepository;

import weka.classifiers.functions.LinearRegression;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.JSONLoader;

@Controller(value = "gestionBudgetController")
public class GestionBudgetController {
	@Autowired
	ClientRepository clientRepository;

	// get list of users with there transactions
	// prepare data
	// {mois,sexe,ageRange,civilState,depense}
	public Date getWhenReachTarget(Long idUser, Float target, Long numeroCompte) throws Exception {
		// get rest money to reach
		Client client = clientRepository.getById(idUser);
		Float cureentSolde = client.getCompteList().stream().filter(c -> numeroCompte.equals(c.getNumeroCompte()))
				.findFirst().get().getSolde();
		Float toReachGap = target - cureentSolde;
		if (toReachGap > 0) {
			Instance instance = null;
			InputStream is = null;
			JSONLoader loader = new JSONLoader();
			LinearRegression linear = new LinearRegression();
			JSONArray data = prepareData();
			Calendar targetDate = Calendar.getInstance();

			// set instance to predict
			ClientInstance clientInstance = prepareClientInstance(client, null, null);

			data.put(clientInstance.toString());

			is = new ByteArrayInputStream(data.toString().getBytes());
			loader.setSource(is);
			Instances instances = loader.getDataSet();
			instances.setClassIndex(instances.numAttributes() - 1);
			linear.buildClassifier(instances);
			instance = instances.get(instance.numAttributes() - 1);
			// get predicted value
			double savingprediction = linear.classifyInstance(instance);
			// test if we have the target amount of money
			cureentSolde = (float) (cureentSolde + getReelSavingMoney(cureentSolde, savingprediction));
			targetDate.add(Calendar.MONTH, 1);
			while (cureentSolde < target) {
				// change the old depense with the predicted value
				clientInstance.setSavings(savingprediction);
				data.put(data.length() - 1, clientInstance.toString());
				// predict depense of the next month
				clientInstance.setMonth(getCurrentMonth() + 1);
				clientInstance.setSavings(null);
				data.put(clientInstance.toString());
				is = new ByteArrayInputStream(data.toString().getBytes());
				loader.setSource(is);
				instances.setClassIndex(instances.numAttributes() - 1);
				linear.buildClassifier(instances);
				savingprediction = linear.classifyInstance(instance);
				targetDate.add(Calendar.MONTH, 1);
			}
			return targetDate.getTime();
		} else {
			// the client already have the amount of money
			return new Date();
		}

	}

	// table for each month how much he needs to save
	public Map<YearMonth, Float> getHowReachTarget(Long idUser, Float targetMoney, YearMonth targetDate,
			Long numeroCompte) {
		Map<YearMonth, Float> savingsSched = Collections.EMPTY_MAP;
		YearMonth currentDate = YearMonth.now();
		// simple service
		if (!currentDate.isBefore(targetDate)) {
			Client client = clientRepository.getById(idUser);
			Float cureentSolde = client.getCompteList().stream().filter(c -> numeroCompte.equals(c.getNumeroCompte()))
					.findFirst().get().getSolde();
			Float toReachGap = targetMoney - cureentSolde;
			if (toReachGap > 0) {
				int nbrOfMonths = Period
						.between(convertToLocalDateViaInstant(new Date()), convertToLocalDateViaInstant(
								new Date(targetDate.getYear(), targetDate.getMonthValue(), targetDate.lengthOfMonth())))
						.getMonths();
				float avg = toReachGap / nbrOfMonths;
				for (int i = 0; i < nbrOfMonths; i++) {
					savingsSched.put(currentDate.plusMonths(i), avg);
				}
			}
		}
		return savingsSched;

	}

	private int getAgeRange(Date date) {
		int age = Period.between(convertToLocalDateViaInstant(date), convertToLocalDateViaInstant(new Date()))
				.getYears();
		if (age >= 18 && age < 24) {
			return 0;
		}
		if (age >= 25 && age < 64) {
			return 1;
		}
		return 2;
	}

	private JSONArray prepareData() {
		Collection<ClientInstance> jsonCollection = Collections.emptyList();
		List<Client> clients = clientRepository.findAll();
		for (Client client : clients) {
			// get savings since the creation of the a compte
			Map<YearMonth, Double> savings = TransactionController.getSavings(client);
			for (YearMonth yearMonth : savings.keySet()) {
				jsonCollection.add(prepareClientInstance(client, yearMonth, savings.get(yearMonth)));
			}
		}
		return new JSONArray(clients);
	}

	private LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	@SuppressWarnings("deprecation")
	private int getCurrentMonth() {
		Date date = new Date();
		return date.getMonth();
	}

	private double getReelSavingMoney(Float currentSolde, Double savings) {
		return (currentSolde * savings) / 100;
	}

	private ClientInstance prepareClientInstance(Client client, YearMonth yearMonth, Double savings) {
		ClientInstance clientInstance = new ClientInstance();
		clientInstance.setCivilState(client.getCivilState());
		clientInstance.setSexe(client.getSexe());
		clientInstance.setAgeRange(getAgeRange(client.getDateOfBirth()));
		if (yearMonth != null && savings != null) {
			clientInstance.setMonth(yearMonth.getMonthValue());
			clientInstance.setSavings(savings);
		} else {
			clientInstance.setMonth(getCurrentMonth() + 1);
		}
		return clientInstance;
	}
}
