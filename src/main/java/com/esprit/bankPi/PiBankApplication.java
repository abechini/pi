package com.esprit.bankPi;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.esprit.bankPi.data.Compte;
import com.esprit.bankPi.data.DepositPojo;
import com.esprit.bankPi.enums.CompteType;
import com.esprit.bankPi.enums.CurrencyEnum;
import com.esprit.bankPi.exceptions.TransactionException;
import com.esprit.bankPi.repository.AgencyRepository;
import com.esprit.bankPi.repository.ClientRepository;
import com.esprit.bankPi.repository.CompteRepository;
import com.esprit.bankPi.repository.DepositRepository;
import com.esprit.bankPi.resources.IDepositService;
import com.esprit.bankPi.data.Income;
import com.esprit.bankPi.enums.CivilState;
import com.esprit.bankPi.enums.CompteType;
import com.esprit.bankPi.enums.Currency;
import com.esprit.bankPi.enums.IncomeType;
import com.esprit.bankPi.enums.Sexe;
import com.esprit.bankPi.repository.AgencyRepository;
import com.esprit.bankPi.repository.ClientRepository;
import com.esprit.bankPi.repository.CompteRepository;
import com.esprit.bankPi.repository.IncomeRepository;
import com.esprit.bankPi.resources.GestionBudgetServiceImpl;

@SpringBootApplication
public class PiBankApplication {
	public PiBankApplication() throws Exception {
		// initialize various settings when start running the springbootapplication
		super();
		try {
			/*
			 * initialize the DB when app starts to run.
			 */
//	            ChatBotSearchUtil.MySQLInitTable("Small");
//	            ChatBotSearchUtil.MySQLInitTable("Medium");
//	            ChatBotSearchUtil.MySQLInitTable("Large");
//	            ChatBotSearchUtil.MySQLParseXML("Small");
//	            ChatBotSearchUtil.MySQLParseXML("Medium");
//	           ChatBotSearchUtil.MySQLParseXML("Large");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ClientRepository clientRepository;
	public static AgencyRepository agencyRepository;
	public static CompteRepository compteRepository;
	static IDepositService depositService;
	static DepositRepository depositRepository;
	
	@Autowired
	public void setdepoRepository(IDepositService repo) {
		this.depositService = repo;
	}
	public static IncomeRepository incomeRepository;

	@Autowired
	public void setdeposervice(DepositRepository repo) {
		this.depositRepository = repo;
	}

	@Autowired
	public void setClientRepository(ClientRepository repo) {
		this.clientRepository = repo;
	}

	@Autowired
	public void setAgencyRepository(AgencyRepository repo) {
		this.agencyRepository = repo;
	}

	@Autowired
	public void setCompteRepo(CompteRepository repo) {
		this.compteRepository = repo;
	}


	public static void main(String[] args) throws TransactionException {

		SpringApplication.run(PiBankApplication.class, args);
		
		Compte c = new Compte();
		c.setSolde(20d);
		c.setCurrency(CurrencyEnum.TND);
		c.setType(CompteType.DEPOSIT);
		c.setNumeroCompte(2l);
		
		compteRepository.save(c);
		
		for ( int i =0; i <5 ; i++ ) {
    public void setCompteRepo(CompteRepository repo){
        this.compteRepository= repo;
    }
	@Autowired
    public void setIncomeRepository(IncomeRepository repo){
        this.incomeRepository= repo;
    }
	public static void main(String[] args) {
		
		SpringApplication.run(PiBankApplication.class, args);
		
		for(int i=0;i<200 ; i++) {
			Client client = new Client();
			Agency agency = new Agency();
			Compte compte = new Compte();
			Income income = new Income();
			List<Income> listIncome = new ArrayList<Income>();
			income.setIncomeType(IncomeType.SALARY);
			income.setIncomeEndDate(YearMonth.of(2050, 12));
			income.setIncomeAmount(Math.random()*100);
			List<Compte> list = new ArrayList<Compte>();
			compte.setCurrency(Currency.TND);
			listIncome.add(income);
			compte.setIncomes(listIncome);
			//compte.setClient(client);
			agency.setName("amen");
			client.setAddress("tunis");
			client.setSexe(Sexe.Male);
			client.setAgency(agency);
			list.add(compte);
			client.setCompteList(list);;
			client.setCivilState(CivilState.Married);
			client.setDateOfBirth(new Date());
			client.setEmail("aziz@bechini.tn");
			agencyRepository.save(agency);
			client.setFirstName("aziz"+Math.random()*100);
			client.setName("bechini");
			compte.setSolde((Double) Math.random()*100);
			compte.setType(CompteType.SAVING);
			client.setCin(Math.random()+"");
			//incomeRepository.save(income);
			clientRepository.save(client);
			//compteRepository.save(compte);

			
			DepositPojo d = new DepositPojo(); 
			d.setTransaction_date(new Date());
			d.setCompte(c);
			d.setAmount_in_number(i);
			d.setCurrency(CurrencyEnum.TND);
			
//			depositRepository.save(d);
			
			depositService.deposit(i+5, CurrencyEnum.TND, c.getNumeroCompte());
		}

//		for (int i = 0; i < 10; i++) {
//			Client client = new Client();
//			Agency agency = new Agency();
//			Compte compte = new Compte();
//			List<Compte> list = new ArrayList<Compte>();
//			compte.setCurrency(Currency.TND);
//			agency.setName("amen");
//			client.setAddress("tunis");
//			client.setSexe(Sexe.Male);
//			client.setAgency(agency);
//			list.add(compte);
//			client.setCompteList(list);
//			;
//			client.setCivilState(CivilState.Married);
//			client.setDateOfBirth(new Date());
//			client.setEmail("aziz@bechini.tn");
//			agencyRepository.save(agency);
//			client.setFirstName("aziz" + Math.random() * 100);
//			client.setName("bechini");
//			compte.setSolde((float) Math.random() * 100);
//			compte.setType(CompteType.SAVING);
//			client.setCin(Math.random() + "");
//			compteRepository.save(compte);
//			clientRepository.save(client);
//
//		}
	}

}
