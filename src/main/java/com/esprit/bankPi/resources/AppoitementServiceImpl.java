package com.esprit.bankPi.resources;

import com.esprit.bankPi.data.Agent;
import com.esprit.bankPi.data.Appoitement;
import com.esprit.bankPi.data.Client;
import com.esprit.bankPi.data.WorkingPlan;
import com.esprit.bankPi.enums.AppointmentStatus;
import com.esprit.bankPi.enums.AppoitementType;

import com.esprit.bankPi.model.DayPlan;
import com.esprit.bankPi.model.TimePeroid;
import com.esprit.bankPi.repository.AgencyRepository;
import com.esprit.bankPi.repository.AgentRepository;
import com.esprit.bankPi.repository.AppoitementRepository;
import com.esprit.bankPi.repository.ClientRepository;
import com.esprit.bankPi.repository.WorkingPlanRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppoitementServiceImpl implements IAppoitementService {
	@Autowired
	AppoitementRepository appoitementRepository;
	@Autowired
	AgentRepository AgentRepository;
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	AgencyRepository agencyRepository;
	@Autowired
	WorkingPlanRepository workingPlanRepository;

	@SuppressWarnings("static-access")
	@Override
	public List<TimePeroid> getAvailableHoursByAgent(Long agentId, LocalDate date) {
		workingPlanRepository.deleteAll();
		Agent agent = AgentRepository.findAgentById(agentId);
		WorkingPlan defaultWorkingPlan = new WorkingPlan();
		defaultWorkingPlan = defaultWorkingPlan.generateDefaultWorkingPlan();
		defaultWorkingPlan.setAgent(agent);
		if (agent.getWorkingPlan() == null) {
			workingPlanRepository.save(defaultWorkingPlan);
		}
		DayPlan selectedDay = defaultWorkingPlan.getDay(date.getDayOfWeek().toString().toLowerCase());
		List<Appoitement> agentAppointments = getAppointmentsByAgentAtDay(agentId, date);
		List<TimePeroid> availablePeroids = selectedDay.getTimePeroidsWithBreaksExcluded();
		availablePeroids = excludeAppointmentsFromTimePeroids(availablePeroids, agentAppointments);
		return calculateAvailableHours(availablePeroids);

	}

	@Override
	public List<TimePeroid> calculateAvailableHours(List<TimePeroid> availableTimePeroids) {
		ArrayList<TimePeroid> availableHours = new ArrayList();
		for (TimePeroid peroid : availableTimePeroids) {
			TimePeroid workPeroid = new TimePeroid(peroid.getStart(), peroid.getStart().plusMinutes(60));
			while (workPeroid.getEnd().isBefore(peroid.getEnd()) || workPeroid.getEnd().equals(peroid.getEnd())) {
				availableHours.add(new TimePeroid(workPeroid.getStart(), workPeroid.getStart().plusMinutes(60)));
				workPeroid.setStart(workPeroid.getStart().plusMinutes(60));
				workPeroid.setEnd(workPeroid.getEnd().plusMinutes(60));
			}
		}
		return availableHours;
	}

	@Override
	public List<Appoitement> getAppointmentClientsByAgentAtDay(long agentId, LocalDate day) {
		return appoitementRepository.findAllAppointmentWithStartInPeroid(agentId, day.atStartOfDay(),
				day.atStartOfDay().plusDays(1));
	}

	@Override
	public List<Appoitement> getAppointmentsByAgentAtDay(Long agentId, LocalDate day) {
		return appoitementRepository.findByProviderIdWithStartInPeroid(agentId, day.atStartOfDay(),
				day.atStartOfDay().plusDays(1));
	}

	@Override
	public List<TimePeroid> excludeAppointmentsFromTimePeroids(List<TimePeroid> peroids,
			List<Appoitement> appointments) {
		List<TimePeroid> toAdd = new ArrayList();
		Collections.sort(appointments);
		for (Appoitement appointment : appointments) {
			for (TimePeroid peroid : peroids) {
				if ((appointment.getStart().toLocalTime().isBefore(peroid.getStart())
						|| appointment.getStart().toLocalTime().equals(peroid.getStart()))
						&& appointment.getEnd().toLocalTime().isAfter(peroid.getStart())
						&& appointment.getEnd().toLocalTime().isBefore(peroid.getEnd())) {
					peroid.setStart(appointment.getEnd().toLocalTime());
				}
				if (appointment.getStart().toLocalTime().isAfter(peroid.getStart())
						&& appointment.getStart().toLocalTime().isBefore(peroid.getEnd())
						&& appointment.getEnd().toLocalTime().isAfter(peroid.getEnd())
						|| appointment.getEnd().toLocalTime().equals(peroid.getEnd())) {
					peroid.setEnd(appointment.getStart().toLocalTime());
				}
				if (appointment.getStart().toLocalTime().isAfter(peroid.getStart())
						&& appointment.getEnd().toLocalTime().isBefore(peroid.getEnd())) {
					toAdd.add(new TimePeroid(peroid.getStart(), appointment.getStart().toLocalTime()));
					peroid.setStart(appointment.getEnd().toLocalTime());
				}
			}
		}
		peroids.addAll(toAdd);
		Collections.sort(peroids);
		return peroids;
	}



	@Override
	public void createNewAppointment(long idClient, LocalDateTime start, AppoitementType appoitementType) {
	   	if (isAvailable(start, idClient)) {
			Client client = clientRepository.findClientById(idClient);
			Appoitement appointment = new Appoitement();
			List<Agent> availbleAgents = getAvailbleAgents(idClient, start);
			if(!availbleAgents.isEmpty())
			{
			appointment.setClient(client);
			appointment.setAgent(availbleAgents.get(0));
			appointment.setState(AppointmentStatus.SCHEDULED);
			appointment.setTypes(appoitementType);
			appointment.setStart(start);
			appointment.setClient(client);
			appointment.setEnd(start.plusMinutes(60));
			long appId = 5L;
			appointment.setId(appId);
			appoitementRepository.save(appointment);
			
			}else {
				throw new RuntimeException();
			}
		} else {
			throw new RuntimeException();
		}

	}

	@Override
	public boolean isAvailable(LocalDateTime start, Long clientId) {
		TimePeroid timePeroid = new TimePeroid(start.toLocalTime(), start.toLocalTime().plusMinutes(60));
		return getAvailableHours(clientId, start.toLocalDate()).contains(timePeroid);
	}

	public List<Agent> getAvailbleAgents(long clientId, LocalDateTime date) {
		Client client = clientRepository.findClientById(clientId);

		List<Agent> agents = AgentRepository.findAllByAgencyId(client.getAgency().getId());

		List<Agent> availbaleAgents = new ArrayList();
		for (int i = 0; i < agents.size(); i++) {
			TimePeroid  timePeroid = new TimePeroid();
			timePeroid.setStart(date.toLocalTime());
			timePeroid.setEnd((date.toLocalTime().plusMinutes(60)));
		   	if (getAvailableHoursByAgent(agents.get(i).getId(), date.toLocalDate()).contains(timePeroid)) {
				availbaleAgents.add(agents.get(i));
			}

		}
		return availbaleAgents;

	}

	public List<TimePeroid> getAvailableHours(long clientId, LocalDate date) {

		Client client = clientRepository.findClientById(clientId);

		List<Agent> agents = AgentRepository.findAllByAgencyId(client.getAgency().getId());

		List<TimePeroid> totalAvailableHours = new ArrayList();
		for (int i = 0; i < agents.size(); i++) {
			totalAvailableHours = Stream
					.concat(totalAvailableHours.stream(),
							getAvailableHoursByAgent(agents.get(i).getId(), date).stream())
					.collect(Collectors.toList());
		}

		return totalAvailableHours;

	}

	@Override
	public List<Appoitement> findAll() {
		return appoitementRepository.findAll();
	}

}
