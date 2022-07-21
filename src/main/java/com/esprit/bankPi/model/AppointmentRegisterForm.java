package com.esprit.bankPi.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class AppointmentRegisterForm {

	private Long agentId;


	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime start;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime end;

	public AppointmentRegisterForm() {
	}

	public AppointmentRegisterForm(Long agentId, LocalDateTime start, LocalDateTime end) {

		this.start = start;
		this.end = end;
	}

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
}
