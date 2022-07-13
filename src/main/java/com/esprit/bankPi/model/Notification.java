package com.esprit.bankPi.model;

import com.esprit.bankPi.enums.NotificationType;
//import org.softib.bank.model.security.Users;
import com.esprit.bankPi.util.Patterns;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Notification {
	String content;
    NotificationType notificationType;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    @DateTimeFormat(pattern = Patterns.DATE_TIME_FORMAT)
   private Date timestamp;
    /*
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
   
    private Users users;

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }*/
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
