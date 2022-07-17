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
	 @Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE)
	 @Column(name = "id", unique = false, nullable = true, insertable = true, updatable = true)
	    public int id;
	    @Column(name = "Name", unique = false, nullable = true, insertable = true, updatable = true)
		 public String Name;
		 @Column(name = "solution", unique = false, nullable = true, insertable = true, updatable = true)
		private String solution;
		 @Column(name = "Date", unique = false, nullable = true, insertable = true, updatable = true)
		private String Date;
		public String getName() {
			return Name;
		}
		public void setName(String name) {
			Name = name;
		}
		public String getSolution() {
			return solution;
		}
		public void setSolution(String solution) {
			this.solution = solution;
		}
		public String getDate() {
			return Date;
		}
		public void setDate(String date) {
			Date = date;
		}


}
