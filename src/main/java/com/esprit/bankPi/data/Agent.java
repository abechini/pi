package com.esprit.bankPi.data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


import java.util.List;

@Entity
public class Agent {
 @Id
    private Long id ;
	private String lastName;
	private String firstName;
    private  String address ;
    @Column(columnDefinition = "boolean default true")
    private boolean status ; 
    @OneToMany
    List<Appoitement> appoitementList ;
    
    @OneToOne(mappedBy = "agent", cascade = {CascadeType.ALL})
    private WorkingPlan workingPlan;
    @ManyToOne
    Agency agency ;

    public WorkingPlan getWorkingPlan() {
		return workingPlan;
	}

	public void setWorkingPlan(WorkingPlan workingPlan) {
		this.workingPlan = workingPlan;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	public Agent() {

    }

    public Agent(String lastName, String firstName , String address,Agency agency) {
        this.lastName = lastName ;
        this.firstName = firstName ;
        this.address = address;
        this.agency = agency;
    }

    public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public List<Appoitement> getAppoitementList() {
        return appoitementList;
    }

    public void setAppoitementList(List<Appoitement> appoitementList) {
        this.appoitementList = appoitementList;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

  

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

  



}
