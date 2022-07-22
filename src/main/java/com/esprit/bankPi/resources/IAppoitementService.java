package com.esprit.bankPi.resources;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.esprit.bankPi.data.Appoitement;
import com.esprit.bankPi.enums.AppoitementType;
import com.esprit.bankPi.model.TimePeroid;

@Service
public interface IAppoitementService {
	public List<TimePeroid> getAvailableHours(long clientId, LocalDate date);

	public List<TimePeroid> getAvailableHoursByAgent(Long agentId, LocalDate date);

	public List<Appoitement> getAppointmentsByAgentAtDay(Long agentId, LocalDate day);

	public List<TimePeroid> calculateAvailableHours(List<TimePeroid> availableTimePeroids);

	public List<TimePeroid> excludeAppointmentsFromTimePeroids(List<TimePeroid> peroids,
			List<Appoitement> appointments);

	public List<Appoitement> getAppointmentClientsByAgentAtDay(long agentId, LocalDate day);

	public void createNewAppointment(long idClient, LocalDateTime start, AppoitementType appoitementType);

	public boolean isAvailable(LocalDateTime start, Long clientId);

	public List<Appoitement> findAll();
}
