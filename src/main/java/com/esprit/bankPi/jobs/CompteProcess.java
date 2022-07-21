package com.esprit.bankPi.jobs;

import org.springframework.batch.item.ItemProcessor;

import com.esprit.bankPi.data.Compte;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CompteProcess implements ItemProcessor<Compte, Compte> {
	@Override
	public Compte process(Compte item) throws Exception {
		log.info("Account solde now :" + item.getSolde());
		item.setSolde(item.getSolde() - item.getAcountFees());
		log.info("Account solde after process:" + item.getSolde());
		return item;
	}
}
