package com.esprit.bankPi.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Withdraw")
public class WithdrowPojo extends TransactionPojo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;

}
