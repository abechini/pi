package com.esprit.bankPi.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

	@GetMapping("/api/hello")
	public ResponseEntity<String> hello(Authentication authentication) {
		final String body = "hello" + authentication.getName();
		return ResponseEntity.ok(body);
	}

	@RequestMapping(path = "/admin", method = RequestMethod.GET, // @RequestMapping default assignment
			produces = MediaType.APPLICATION_JSON_VALUE // TIP : use org.springframework.http.MediaType for MimeType
														// instead of hard coded value
	)
	public String adminSecuredEndpoint() {
		return "This is an ADMIN endpoint payload";
	}

	@RequestMapping("/client")
	public String userSecuredEndpoint() {
		return "This is a CLIENT endpoint payload";
	}

	@RequestMapping("/agent")
	public String agentSecuredEndpoint() {
		return "This is an AGENT endpoint payload";
	}

	@GetMapping(path = "/logout")
	public String logout(HttpServletRequest request) throws ServletException {
		request.logout();
		return "/";
	}
}
