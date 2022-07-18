package com.esprit.bankPi.controller;

import org.apache.camel.builder.RouteBuilder;

import org.springframework.stereotype.Component;

@Component
public class SendComplaintNotification extends RouteBuilder {

	public void configure() throws Exception {
		// restConfiguration().host("127.0.0.1:8085/api");

		// addRestConfiguration(restConfiguration);

		from("jetty:http://127.0.0.1:8086/api/complaint/addCompaint").doTry()
				.setHeader("subject", simple("New Appointement"))
				// from("rest:get:complaint/getAllComplainByState:/{ACTIVE}")
				.setHeader("to", simple("maissa.benromdhane@esprit.tn"))
				// .to("file:C:\\Users\\mbenromdhane\\Downloads\\PIcamel\\out");
				.to("smtps://smtp.gmail.com:465?username=maissa.benromdhane@esprit.tn&password=193JFT2496&to=maissa.benromdhane@esprit.tn");

	}

}
