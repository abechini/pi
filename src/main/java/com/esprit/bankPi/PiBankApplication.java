package com.esprit.bankPi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.esprit.bankPi.controller.GestionBudgetController;
import com.esprit.bankPi.data.Agency;
import com.esprit.bankPi.data.Client;
import com.esprit.bankPi.data.Compte;
import com.esprit.bankPi.enums.CivilState;
import com.esprit.bankPi.enums.CompteType;
import com.esprit.bankPi.enums.Currency;
import com.esprit.bankPi.enums.Sexe;
import com.esprit.bankPi.repository.AgencyRepository;
import com.esprit.bankPi.repository.ClientRepository;
import com.esprit.bankPi.repository.CompteRepository;
import com.esprit.bankPi.resources.GestionBudgetServiceImpl;

@SpringBootApplication
public class PiBankApplication {
	
	public static ClientRepository clientRepository ;
	public static AgencyRepository agencyRepository;
	public static CompteRepository compteRepository;
	@Autowired
    public void setClientRepository(ClientRepository repo){
        this.clientRepository= repo;
    }
	@Autowired
    public void setAgencyRepository(AgencyRepository repo){
        this.agencyRepository= repo;
    }
	@Autowired
    public void setCompteRepo(CompteRepository repo){
        this.compteRepository= repo;
    }
	public static void main(String[] args) {
		
		SpringApplication.run(PiBankApplication.class, args);
		
		for(int i=0;i<10 ; i++) {
			Client client = new Client();
			Agency agency = new Agency();
			Compte compte = new Compte();
			List<Compte> list = new ArrayList<Compte>();
			compte.setCurrency(Currency.TND);
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
			compte.setSolde((float) Math.random()*100);
			compte.setType(CompteType.SAVING);
			client.setCin(Math.random()+"");
			compteRepository.save(compte);
			clientRepository.save(client);
			
		}
	}

}
