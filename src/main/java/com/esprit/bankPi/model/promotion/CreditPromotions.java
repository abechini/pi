package com.esprit.bankPi.model.promotion;

import com.esprit.bankPi.data.Compte;

import javax.persistence.*;

@Entity
public class CreditPromotions extends Promotions {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    private double interest;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "numeroCompte")
    private Compte role;

    public CreditPromotions() {
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public Compte getRole() {
        return role;
    }

    public void setRole(Compte role) {
        this.role = role;
    }
}
