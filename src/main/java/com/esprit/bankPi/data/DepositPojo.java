package com.esprit.bankPi.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="data_Deposit")
@Data
public class DepositPojo extends TransactionPojo{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
}
