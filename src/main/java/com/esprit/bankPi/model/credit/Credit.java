package com.esprit.bankPi.model.credit;
import org.springframework.format.annotation.DateTimeFormat;

import com.esprit.bankPi.data.Compte;
import com.esprit.bankPi.enums.CreditStatus;
import com.esprit.bankPi.enums.CreditType;

import com.esprit.bankPi.util.Patterns;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
public class Credit {
	 @Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	    @Column(name = "id", nullable = false, updatable = false)
	    private Long id;
	    @DateTimeFormat(pattern = Patterns.DATE_FORMAT)
	    private LocalDate creationDate;
	    private CreditStatus creditStatus;
	    private Integer creditTerm;
	    private Double creditAmount;
	    private Double creditInterest;
	    private Double creditFees;
	    @DateTimeFormat(pattern = Patterns.DATE_FORMAT)
	    private LocalDate releaseDate;
	    private String agent;
	    private CreditType type;
	
	   
	    
	    
	    @OneToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "creditrequest_id")
	    private CreditRequest creditRequest;
	    
	
	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "numeroCompte")
	    private Compte account;

	  
	    public Credit() {
	    }
	    

	    public Compte getAccount() {
	        return account;
	    }

	    public void setAccount(Compte account) {
	        this.account = account;
	    }

	

	    public CreditRequest getCreditRequest() {
	        return creditRequest;
	    }

	    public void setCreditRequest(CreditRequest creditRequest) {
	        this.creditRequest = creditRequest;
	    }

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }



	    public CreditStatus getCreditStatus() {
	        return creditStatus;
	    }

	    public void setCreditStatus(CreditStatus creditStatus) {
	        this.creditStatus = creditStatus;
	    }

	    public Integer getCreditTerm() {
	        return creditTerm;
	    }

	    public void setCreditTerm(Integer creditTerm) {
	        this.creditTerm = creditTerm;
	    }

	    public Double getCreditAmount() {
	        return creditAmount;
	    }

	    public void setCreditAmount(Double creditAmount) {
	        this.creditAmount = creditAmount;
	    }

	  

	    public Double getCreditInterest() {
	        return creditInterest;
	    }

	    public void setCreditInterest(Double creditInterest) {
	        this.creditInterest = creditInterest;
	    }

	    public Double getCreditFees() {
	        return creditFees;
	    }

	    public void setCreditFees(Double creditFees) {
	        this.creditFees = creditFees;
	    }

	    public LocalDate getCreationDate() {
	        return creationDate;
	    }

	    public void setCreationDate(LocalDate creationDate) {
	        this.creationDate = creationDate;
	    }

	    public LocalDate getReleaseDate() {
	        return releaseDate;
	    }

	    public void setReleaseDate(LocalDate releaseDate) {
	        this.releaseDate = releaseDate;
	    }

	    public String getAgent() {
	        return agent;
	    }

	    public void setAgent(String agent) {
	        this.agent = agent;
	    }

	    public CreditType getType() {
	        return type;
	    }

	    public void setType(CreditType type) {
	        this.type = type;
	    }


	
	
}
