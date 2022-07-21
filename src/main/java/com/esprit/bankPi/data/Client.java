package com.esprit.bankPi.data;

import javax.persistence.*;

import com.esprit.bankPi.enums.CivilState;
import com.esprit.bankPi.enums.Sexe;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="data_Client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "cin", unique = false, nullable = false, insertable = true, updatable = true)
    private String cin ;
    @Column(name = "datebirth", unique = false, nullable = false, insertable = true, updatable = true)
    private Date dateOfBirth;
    @Column(name = "name", unique = false, nullable = false, insertable = true, updatable = true)
    private String name ;
    @Column(name = "firstname", unique = false, nullable = false, insertable = true, updatable = true)
    private String firstName ;
    @Column(name = "address", unique = false, nullable = false, insertable = true, updatable = true)
    private String  address ;
    @Column(name = "email", unique = false, nullable = false, insertable = true, updatable = true)
    private String  email ;
    @Column(name = "civilState", unique = false, nullable = false, insertable = true, updatable = true)
    private CivilState civilState ;
    @Column(name = "sexe", unique = false, nullable = false, insertable = true, updatable = true)
    private Sexe sexe ;
    @OneToMany
    List<Appoitement> appoitementList ;
    @OneToMany
    List<Complaint>  complaintList;
    @JsonIgnoreProperties("client")
    @OneToMany(cascade = CascadeType.ALL)
    List<Compte>  compteList = new ArrayList<Compte>();
    @ManyToOne
    Agency agency ;

    public List<Appoitement> getAppoitementList() {
        return appoitementList;
    }

    public void setAppoitementList(List<Appoitement> appoitementList) {
        this.appoitementList = appoitementList;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public List<Complaint> getComplaintList() {
        return complaintList;
    }

    public void setComplaintList(List<Complaint> complaintList) {
        this.complaintList = complaintList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }




    public CivilState getCivilState() {
        return civilState;
    }

    public void setCivilState(CivilState civilState) {
        this.civilState = civilState;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    private String numTel ;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Sexe getSexe() {
		return sexe;
	}

	public void setSexe(Sexe sexe) {
		this.sexe = sexe;
	}

	public List<Compte> getCompteList() {
		return compteList;
	}

	public void setCompteList(List<Compte> compteList) {
		this.compteList = compteList;
	}

	public Client() {
    }
}
