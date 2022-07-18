
package com.esprit.bankPi.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.esprit.bankPi.data.Client;
import com.esprit.bankPi.enums.CivilState;
import com.esprit.bankPi.enums.Sexe;
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
	public YearMonth getWhenReachTarget(Long idUser, Float target, Long numeroCompte) throws Exception {
		// get rest money to reach
		Client client = clientRepository.getById(idUser);
		Double cureentSolde = client.getCompteList().stream().filter(c -> numeroCompte.equals(c.getNumeroCompte()))
				.findFirst().get().getSolde();
		double toReachGap = target - cureentSolde;
		if (toReachGap > 0) {
			Instance instance = null;
			InputStream is = null;
			JSONLoader loader = new JSONLoader();
			LinearRegression linear = new LinearRegression();
			//JSONArray data = prepareData();
			JSONObject data = prepareData();
			Calendar targetDate = Calendar.getInstance();

			// set instance to predict
			ClientInstance clientInstance = prepareClientInstance(client, null, null);

			//data.accumulate("data",clientInstance.toString());
			data.accumulate("data", clientInstance.toJsonObject());
			is = new ByteArrayInputStream(data.toString().getBytes());
			
			loader.setSource(is);
			Instances instances = loader.getDataSet();
			instances.setClassIndex(instances.numAttributes() - 1);
			linear.buildClassifier(instances);
			instance = instances.get(instances.numAttributes() - 1);
			// get predicted value
			double savingprediction = linear.classifyInstance(instance);
			// test if we have the target amount of money
			cureentSolde = (Double) (cureentSolde + getReelSavingMoney(cureentSolde, savingprediction));
			targetDate.add(Calendar.MONTH, 1);
			while (cureentSolde < target) {
				// change the old depense with the predicted value
				clientInstance.setSavings(savingprediction);
				data.getJSONArray("data").put(data.length() - 1, clientInstance.toJsonObject());
				//data.put(data.length() - 1, clientInstance.toString());
				// predict depense of the next month
				ClientInstance newInst = clientInstance.clone();
				newInst.setMonth(newInst.getMonth()+1);
				newInst.setSavings(null);
				data.getJSONArray("data").put(newInst.toJsonObject());
				is = new ByteArrayInputStream(data.toString().getBytes());
				loader.setSource(is);
				instances.setClassIndex(instances.numAttributes() - 1);
				linear.buildClassifier(instances);
				savingprediction = linear.classifyInstance(instance);
				
				System.out.println("Date to predicate : "+YearMonth.from(convertToLocalDateViaInstant(targetDate.getTime()))+ " current balance : "+cureentSolde+" saving prediction : "+getReelSavingMoney(cureentSolde, savingprediction));
				
				targetDate.add(Calendar.MONTH, 1);
				cureentSolde = (Double) (cureentSolde + getReelSavingMoney(cureentSolde, savingprediction));
			}
			return YearMonth.from(convertToLocalDateViaInstant(new Date(targetDate.getTime().getTime())));
		} else {
			// the client already have the amount of money
			return  YearMonth.from(convertToLocalDateViaInstant(new Date()));
		}

	}

	// table for each month how much he needs to save
	public Map<YearMonth, Double> getHowReachTarget(Long idUser, Float targetMoney, YearMonth targetDate,
			Long numeroCompte) {
		Map<YearMonth, Double> savingsSched = new HashMap<YearMonth, Double>();
		YearMonth currentDate = YearMonth.now();
		// simple service
		if (currentDate.isBefore(targetDate)) {
			Client client = clientRepository.getById(idUser);
			Double cureentSolde = client.getCompteList().stream().filter(c -> numeroCompte.equals(c.getNumeroCompte()))
					.findFirst().get().getSolde();
			double toReachGap = targetMoney - cureentSolde;
			if (toReachGap > 0) {
				Period period = Period
						.between(convertToLocalDateViaInstant(new Date()), targetDate.atDay(targetDate.lengthOfMonth()));
				int nbrOfMonths = period.getMonths()+(period.getYears()*12)+(period.getDays()>0?1:0);
				double avg = toReachGap / nbrOfMonths;
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

	private JSONObject prepareData() throws Exception{
		JSONObject json = new JSONObject();
		JSONArray data = new JSONArray();
//		JSONObject dataObject = new JSONObject();
		JSONObject clientsJsonObject = new JSONObject();
		clientsJsonObject.put("relation", "client");
		clientsJsonObject.put("attributes", getJsonAtt());
		//add json header
		//json.put("clients", getJsonAtt());
//		Collection<ClientInstance> jsonCollection =new  ArrayList<ClientInstance>();
		List<Client> clients = clientRepository.findAll();
		for (Client client : clients) {
			// get savings since the creation of the a compte
			Map<YearMonth, Double> savings = TransactionController.getSavings(client);
			
			for (YearMonth yearMonth : savings.keySet()) {
				data.put(prepareClientInstance(client, yearMonth, savings.get(yearMonth)).toJsonObject());
			}
		}
		//clientsJsonObject.put("data", data);
		//return new JSONArray(jsonCollection);
		json.put("header", clientsJsonObject);
		json.put("data", data);
		return json;
		 
	}

	private LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	@SuppressWarnings("deprecation")
	private int getCurrentMonth() {
		Date date = new Date();
		return date.getMonth();
	}

	private double getReelSavingMoney(Double cureentSolde, Double savings) {
		return (cureentSolde * savings) / 100;
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
	private JSONObject getJsonAtt() throws JSONException {	
		
		JSONObject monthObject = new JSONObject();
		monthObject.put("name", "month");
		monthObject.put("type", "numeric");
		monthObject.put("weight",Collections.EMPTY_LIST);
		
		JSONObject ageRangeObject = new JSONObject();
		ageRangeObject.put("name", "ageRange");
		ageRangeObject.put("type", "numeric");
		ageRangeObject.put("weight", Collections.EMPTY_LIST);
		
		JSONObject savingsObject = new JSONObject();
		savingsObject.put("name", "savings");
		savingsObject.put("type", "numeric");
		savingsObject.put("weight", Collections.EMPTY_LIST);
		
		JSONObject sexeObject = new JSONObject();
		sexeObject.put("name", "sexe");
		sexeObject.put("type", "numeric");
		sexeObject.put("weight", Collections.EMPTY_LIST);
		
		JSONObject civilStateObject = new JSONObject();
		civilStateObject.put("name", "civilState");
		civilStateObject.put("type", "numeric");
		civilStateObject.put("weight",Collections.EMPTY_LIST);


		JSONObject attributes = new JSONObject();
		attributes.put("month", monthObject);
		attributes.put("sexe", sexeObject);
		attributes.put("ageRange", ageRangeObject);
		attributes.put("civilState", civilStateObject);
		attributes.put("savings", savingsObject);
		return attributes;

	}
}
