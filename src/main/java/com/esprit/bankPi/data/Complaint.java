package com.esprit.bankPi.data;

import com.esprit.bankPi.enums.ComplaintPriority;
import com.esprit.bankPi.enums.ComplaintState;
import com.esprit.bankPi.enums.ComplaintTypes;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name="data_Complaint")
public class Complaint {
    @Id
    private Long id ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  //  @Column(nullable=true)
    public Client client;
    
    @Enumerated(EnumType.STRING)
    private ComplaintTypes Types ;
    
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date creationDate ;

    @Column(nullable = true)
    private Date  closingDate ;
    
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(32) default 'WAITING'")
    private ComplaintState currentState = ComplaintState.WAITING;
    
    @Enumerated(EnumType.STRING)
    private ComplaintPriority  priority ;
    
    private String description;

    public ComplaintTypes getTypes() {
        return Types;
    }

    public void setTypes(ComplaintTypes types) {
        Types = types;
    }

    public ComplaintState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(ComplaintState currentState) {
        this.currentState = currentState;
    }

    public ComplaintPriority getPriority() {
        return priority;
    }

    public void setPriority(ComplaintPriority priority) {
        this.priority = priority;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }




    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }





    public Complaint(ComplaintTypes types, ComplaintPriority priority, String description) {
        this.Types = types;
        this.priority = priority;
        this.description = description;
    }
    public Complaint() {

    }

}
