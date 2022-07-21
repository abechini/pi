package com.esprit.bankPi.resources;

import com.esprit.bankPi.data.Agent;
import com.esprit.bankPi.data.Appoitement;
import com.esprit.bankPi.data.WorkingPlan;
import com.esprit.bankPi.model.DayPlan;
import com.esprit.bankPi.model.TimePeroid;
import com.esprit.bankPi.repository.AgentRepository;
import com.esprit.bankPi.repository.AppoitementRepository;
import com.esprit.bankPi.repository.WorkingPlanRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppoitementServiceImpl implements IAppoitementService{
    @Autowired
    AppoitementRepository  appoitementRepository ;
    @Autowired
    AgentRepository  AgentRepository ;
    @Autowired
    WorkingPlanRepository workingPlanRepository ;
	@SuppressWarnings("static-access")
	@Override
	public List<TimePeroid> getAvailableHours(Long agentId, LocalDate date) {
		Agent p = AgentRepository.findAgentById(agentId);
		WorkingPlan defaultWorkingPlan = new WorkingPlan();
		defaultWorkingPlan= defaultWorkingPlan.generateDefaultWorkingPlan() ;
		Agent agent = AgentRepository.findAgentById(agentId);
		defaultWorkingPlan.setAgent(agent);
		workingPlanRepository.save(defaultWorkingPlan);
		/****null ****/ 
        WorkingPlan workingPlan = p.getWorkingPlan();
        /****/
       DayPlan selectedDay = defaultWorkingPlan.getDay(date.getDayOfWeek().toString().toLowerCase());
       /* DayPlan selectedDay = new DayPlan() ;
        selectedDay = workingPlan.getMonday();*/
        
        List<Appoitement> agentAppointments = getAppointmentsByAgentAtDay(agentId, date);
        List<Appoitement> clientsAppointments = getAppointmentClientsAtDay(date);

        List<TimePeroid> availablePeroids = selectedDay.getTimePeroidsWithBreaksExcluded();
        availablePeroids = excludeAppointmentsFromTimePeroids(availablePeroids, agentAppointments);

        availablePeroids = excludeAppointmentsFromTimePeroids(availablePeroids, clientsAppointments);
        return calculateAvailableHours(availablePeroids);

	}

	@Override
	public List<TimePeroid> calculateAvailableHours(List<TimePeroid> availableTimePeroids) {
		ArrayList<TimePeroid> availableHours = new ArrayList();
		for (TimePeroid peroid : availableTimePeroids) {
			TimePeroid workPeroid = new TimePeroid(peroid.getStart(),
					peroid.getStart().plusMinutes(60));
			while (workPeroid.getEnd().isBefore(peroid.getEnd()) || workPeroid.getEnd().equals(peroid.getEnd())) {
				availableHours.add(
						new TimePeroid(workPeroid.getStart(), workPeroid.getStart().plusMinutes(60)));
				workPeroid.setStart(workPeroid.getStart().plusMinutes(60));
				workPeroid.setEnd(workPeroid.getEnd().plusMinutes(60));
			}
		}
		return availableHours;
	}
	
	
	@Override
	public List<Appoitement> getAppointmentClientsAtDay(LocalDate day) {
		return appoitementRepository.findAllAppointmentWithStartInPeroid(day.atStartOfDay(),
				day.atStartOfDay().plusDays(1));
	}
	

	@Override
	public List<Appoitement> getAppointmentsByAgentAtDay(Long agentId, LocalDate day) {
		  return appoitementRepository.findByProviderIdWithStartInPeroid(agentId, day.atStartOfDay(), day.atStartOfDay().plusDays(1));
	}
	@Override
	public List<TimePeroid> excludeAppointmentsFromTimePeroids(List<TimePeroid> peroids,
			List<Appoitement> appointments) {
	     List<TimePeroid> toAdd = new ArrayList();
	        Collections.sort(appointments);
	        for (Appoitement appointment : appointments) {
	            for (TimePeroid peroid : peroids) {
	                if ((appointment.getStart().toLocalTime().isBefore(peroid.getStart()) || appointment.getStart().toLocalTime().equals(peroid.getStart())) && appointment.getEnd().toLocalTime().isAfter(peroid.getStart()) && appointment.getEnd().toLocalTime().isBefore(peroid.getEnd())) {
	                    peroid.setStart(appointment.getEnd().toLocalTime());
	                }
	                if (appointment.getStart().toLocalTime().isAfter(peroid.getStart()) && appointment.getStart().toLocalTime().isBefore(peroid.getEnd()) && appointment.getEnd().toLocalTime().isAfter(peroid.getEnd()) || appointment.getEnd().toLocalTime().equals(peroid.getEnd())) {
	                    peroid.setEnd(appointment.getStart().toLocalTime());
	                }
	                if (appointment.getStart().toLocalTime().isAfter(peroid.getStart()) && appointment.getEnd().toLocalTime().isBefore(peroid.getEnd())) {
	                    toAdd.add(new TimePeroid(peroid.getStart(), appointment.getStart().toLocalTime()));
	                    peroid.setStart(appointment.getEnd().toLocalTime());
	                }
	            }
	        }
	        peroids.addAll(toAdd);
	        Collections.sort(peroids);
	        return peroids;
	    }
	

    
}
