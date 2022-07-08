package com.esprit.bankPi.data;

import com.esprit.bankPi.enums.AppoitementType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="data_Appoitement")
public class Appoitement {
    public Appoitement() {
    }

    @Id
    private Long id;
    private Date date;
    @Enumerated(EnumType.STRING)
    private AppoitementType Types ;
    private  String description ;
    @ManyToOne
    Client client ;
    @ManyToOne
    Agent agent ;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Appoitement(Long id, Date date, AppoitementType types, String description) {
        this.id = id;
        this.date = date;
        Types = types;
        this.description = description;
    }

    public AppoitementType getTypes() {
        return Types;
    }

    public void setTypes(AppoitementType types) {
        Types = types;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




}
