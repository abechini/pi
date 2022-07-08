package com.esprit.bankPi.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Agent {
 @Id
    private Long id ;
    private String name ;
    private  String address ;
    @OneToMany
    List<Appoitement> appoitementList ;


    @OneToMany
    List<Agency> agencyList ;

    public Agent() {

    }

    public Agent(String name, String address, List<Agency> agencyList) {
        this.name = name;
        this.address = address;
        this.agencyList = agencyList;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Agency> getAgencyList() {
        return agencyList;
    }

    public void setAgencyList(List<Agency> agencyList) {
        this.agencyList = agencyList;
    }



}
