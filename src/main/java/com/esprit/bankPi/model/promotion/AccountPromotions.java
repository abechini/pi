package com.esprit.bankPi.model.promotion;

import com.esprit.bankPi.data.Compte;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class AccountPromotions extends Promotions {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;
	private BigDecimal annualFee;
	// points he gets when he open an account
	private BigDecimal introOffer;
	// Bank promotions consist of cash bonuses when you open a new checking or
	// savings account.
	private BigDecimal cashBonuses;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "numeroCompte")
	private Compte role;

	public AccountPromotions() {
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getAnnualFee() {
		return annualFee;
	}

	public void setAnnualFee(BigDecimal annualFee) {
		this.annualFee = annualFee;
	}

	public BigDecimal getIntroOffer() {
		return introOffer;
	}

	public void setIntroOffer(BigDecimal introOffer) {
		this.introOffer = introOffer;
	}

	public BigDecimal getCashBonuses() {
		return cashBonuses;
	}

	public void setCashBonuses(BigDecimal cashBonuses) {
		this.cashBonuses = cashBonuses;
	}

	public Compte getRole() {
		return role;
	}

	public void setRole(Compte role) {
		this.role = role;
	}
}
