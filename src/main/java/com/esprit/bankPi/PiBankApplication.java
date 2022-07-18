package com.esprit.bankPi;

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
