package com.esprit.bankPi.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.bankPi.data.Appoitement;
import com.esprit.bankPi.data.Client;
import com.esprit.bankPi.enums.AppoitementType;
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
		@PostMapping("/new")
		public String bookAppointment(@RequestParam("clientId") int clientId, @RequestParam("start") String start, @RequestParam("appoitementType") AppoitementType appoitementType) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			LocalDateTime localdate = LocalDateTime.parse(start, formatter);
			appointmentService.createNewAppointment(clientId, localdate, appoitementType);
			return "redirect:/appointments/all";
		}
		

		@GetMapping(path = "/appointments/all")
		public List<Appoitement> getAllAppoitements() {
			return appointmentService.findAll();
		}
		@RequestMapping(value = "/redirect", method = RequestMethod.GET)
		public void method(HttpServletResponse httpServletResponse) {
		    httpServletResponse.setHeader("Location", "http://127.0.0.1:8085/appointment/");
		    httpServletResponse.setStatus(302);
		}

}
