package com.esprit.bankPi.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.esprit.bankPi.chatBot.ChatMessage.MessageType;

@Entity
@Table(name="Reclamation")
public class Reclamation {
	private Long id;
	private String solution;
	public String Name;
	private String Date;
	 @Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	
}
