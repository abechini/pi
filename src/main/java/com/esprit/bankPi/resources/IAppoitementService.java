package com.esprit.bankPi.resources;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.esprit.bankPi.data.Appoitement;
import com.esprit.bankPi.model.TimePeroid;

@Service
public interface IAppoitementService {
	  public List<TimePeroid> getAvailableHours(Long agentId, LocalDate date);
	  public List<Appoitement> getAppointmentsByAgentAtDay(Long agentId, LocalDate day) ;
		public List<TimePeroid> calculateAvailableHours(List<TimePeroid> availableTimePeroids) ;
	  public List<Appoitement> getAppointmentClientsAtDay(LocalDate day) ;
	  public List<TimePeroid> excludeAppointmentsFromTimePeroids(List<TimePeroid> peroids, List<Appoitement> appointments) ;
}
