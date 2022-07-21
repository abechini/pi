package com.esprit.bankPi.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.bankPi.data.Client;
import com.esprit.bankPi.model.AppointmentRegisterForm;
import com.esprit.bankPi.resources.AppoitementServiceImpl;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
	@Autowired
	AppoitementServiceImpl appointmentService ;

	 @GetMapping("/availableHours/{agentId}/{date}")
	    public List<AppointmentRegisterForm> getAvailableHours(@PathVariable("agentId") Long agentId, @PathVariable("date") String date) {
	       // LocalDate localdate = Localdate.parse(date) ;
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		 LocalDate localdate = LocalDate.parse(date, formatter);
	        return appointmentService.getAvailableHours(agentId, localdate)
	                .stream()
	                .map(timePeriod -> new AppointmentRegisterForm(agentId, timePeriod.getStart().atDate(localdate), timePeriod.getEnd().atDate(localdate)))
	                .collect(Collectors.toList());
	    }

}
