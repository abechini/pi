package com.esprit.bankPi.data;

import com.esprit.bankPi.enums.AppointmentStatus;
import com.esprit.bankPi.enums.AppoitementType;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "data_Appoitement")
public class Appoitement implements Comparable<Appoitement> {
	public Appoitement() {
	}

	@Id
	private Long id;
	private Date date;

	@Column(name = "start")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime start;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Column(name = "end")
	private LocalDateTime end;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Column(name = "canceled_at", nullable = true)
	private LocalDateTime canceledAt;

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	public LocalDateTime getCanceledAt() {
		return canceledAt;
	}

	public void setCanceledAt(LocalDateTime canceledAt) {
		this.canceledAt = canceledAt;
	}

	
	public AppointmentStatus getState() {
		return state;
	}

	public void setState(AppointmentStatus state) {
		this.state = state;
	}

	

	@Enumerated(EnumType.STRING)
	private AppoitementType Types;
	private String description;

	@Column(columnDefinition = "varchar(32) default 'SCHEDULED'")
	@Enumerated(EnumType.STRING)
	private AppointmentStatus state;
	@ManyToOne
	Client client;
	@ManyToOne
	Agent agent;

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

	@Override
	public int compareTo(Appoitement o) {
		return this.getStart().compareTo(o.getStart());

	}

}
