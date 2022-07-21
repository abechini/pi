package com.esprit.bankPi.model.credit;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.esprit.bankPi.enums.CreditType;
import com.esprit.bankPi.data.Compte;
import com.esprit.bankPi.enums.CreditRequestStatus;

import com.esprit.bankPi.util.Patterns;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CreditRequest {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    private Long cin;
    private String job;
    private String civilState;
    @DateTimeFormat(pattern = Patterns.DATE_FORMAT)
    private Date creationDate;
    private String address;
    private CreditRequestStatus creditRequestStatus;
    private Integer creditTerm;
    private Double creditAmount;
   
   
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "insurance_id")
    private Insurance insurance;
    private CreditType creditType;
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "creditRequest")
    @JsonIgnore
    private Credit credit;
    private Integer age;
    private Double netSalary;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private Compte account;
    private String rejectionReason;

    public CreditRequest() {
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public Compte getAccount() {
        return account;
    }

    public void setAccount(Compte account) {
        this.account = account;
    }

    public Double getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(Double netSalary) {
        this.netSalary = netSalary;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public CreditType getCreditType() {
        return creditType;
    }

    public void setCreditType(CreditType creditType) {
        this.creditType = creditType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public Long getCin() {
        return cin;
    }

    public void setCin(Long cin) {
        this.cin = cin;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCivilState() {
        return civilState;
    }

    public void setCivilState(String civilState) {
        this.civilState = civilState;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CreditRequestStatus getCreditRequestStatus() {
        return creditRequestStatus;
    }

    public void setCreditRequestStatus(CreditRequestStatus creditRequestStatus) {
        this.creditRequestStatus = creditRequestStatus;
    }
}
