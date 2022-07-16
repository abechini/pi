package com.esprit.bankPi.model.promotion;

import com.esprit.bankPi.data.Compte;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class VirtualCardPromotions extends Promotions {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;
	private BigDecimal annualFee;
	// points he gets when he open an account
	private BigDecimal introOffer;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "numeroCompte")
	private Compte role;

	public VirtualCardPromotions() {
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

	public Compte getRole() {
		return role;
	}

	public void setRole(Compte role) {
		this.role = role;
	}
}
