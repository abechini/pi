package com.esprit.bankPi.model.credit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.esprit.bankPi.enums.CreditStatus;
import com.esprit.bankPi.util.Patterns;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Payment {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    @DateTimeFormat(pattern = Patterns.DATE_FORMAT)
    private LocalDate creationDate;
    @DateTimeFormat(pattern = Patterns.DATE_FORMAT)
    private LocalDate paymentDate;
    @DateTimeFormat(pattern = Patterns.DATE_FORMAT)
    private LocalDate paymentDueDate;
    private Double paymentAmount;
    private Double paymentInterest;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "credit_id")
    @JsonIgnore
    private Credit credit;
    private Double penality;
    private CreditStatus paymentStatus;

    public Payment() {
    }

    public CreditStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(CreditStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Double getPenality() {
        return penality;
    }

    public void setPenality(Double penality) {
        this.penality = penality;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public LocalDate getPaymentDueDate() {
        return paymentDueDate;
    }

    public void setPaymentDueDate(LocalDate paymentDueDate) {
        this.paymentDueDate = paymentDueDate;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Double getPaymentInterest() {
        return paymentInterest;
    }

    public void setPaymentInterest(Double paymentInterest) {
        this.paymentInterest = paymentInterest;
    }
}
