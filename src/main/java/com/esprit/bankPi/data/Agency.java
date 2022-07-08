package com.esprit.bankPi.data;

import javax.persistence.*;
import java.util.List;

@Entity
public class Agency {
    @Id
    private Long id ;
    private String name ;
    private String firstName ;
    @OneToMany
    private List<Client> clientList ;
    @OneToOne
    Agent agent ;


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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }



}
