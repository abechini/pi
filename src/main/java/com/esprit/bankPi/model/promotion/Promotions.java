package com.esprit.bankPi.model.promotion;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.esprit.bankPi.util.SystemMessages;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Promotions {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(nullable = false, updatable = false)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Long id;
	@Positive(message = SystemMessages.PROMO_AGE_FORMAT)
	private int age = 18;
	private BigDecimal balance;
	private int rating;
	private String accountType;
	// year since member
	private int yearsDom;

	public Promotions() {
	}

	public Long getId() {
		return id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public int getYearsDom() {
		return yearsDom;
	}

	public void setYearsDom(int yearsDom) {
		this.yearsDom = yearsDom;
	}
}
