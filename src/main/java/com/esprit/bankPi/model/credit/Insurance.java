package com.esprit.bankPi.model.credit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.esprit.bankPi.enums.CreditType;
import com.esprit.bankPi.util.Patterns;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
@Entity
public class Insurance {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    @DateTimeFormat(pattern = Patterns.DATE_FORMAT)
    private Date creationDate;
    @DateTimeFormat(pattern = Patterns.DATE_FORMAT)
    private Date expiryDate;
    private CreditType type;
    private Double amount;
 
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "insurance")
    @JsonIgnore
    private CreditRequest creditRequest;

    public Insurance() {
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public CreditType getType() {
        return type;
    }

    public void setType(CreditType type) {
        this.type = type;
    }
}
