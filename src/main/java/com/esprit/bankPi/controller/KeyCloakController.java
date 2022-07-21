package com.esprit.bankPi.controller;

import java.util.List;

import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.bankPi.data.UserDTO;
import com.esprit.bankPi.service.KeyCloakService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/keycloakController")
public class KeyCloakController {
	@Autowired
	KeyCloakService service;

	@PostMapping(value = "/addUser")
	public String addUser(@RequestBody UserDTO userDTO) {
		service.addUser(userDTO);
		return "User Added Successfully.";
	}

	@GetMapping(path = "/{userName}")
	public List<UserRepresentation> getUser(@PathVariable("userName") String userName) {
		List<UserRepresentation> user = service.getUser(userName);
		return user;
	}

	@PutMapping(path = "/update/{userName}")
	public String updateUser(@PathVariable("userName") String userName, @RequestBody UserDTO userDTO) {
		service.updateUser(userName, userDTO);
		return "User Details Updated Successfully.";
	}

	@DeleteMapping(path = "/{userId}")
	public String deleteUser(@PathVariable("userId") String userId) {
		service.deleteUser(userId);
		return "User Deleted Successfully.";
	}

	@GetMapping(path = "/verification-link/{userName}")
	public String sendVerificationLink(@PathVariable("userName") String userName) {
		service.sendVerificationLink(userName);
		return "Verification Link Send to Registered E-mail Id.";
	}

	@GetMapping(path = "/reset-password/{userName}")
	public String sendResetPassword(@PathVariable("userName") String userName) {
		service.sendResetPassword(userName);
		return "Reset Password Link Send Successfully to Registered E-mail Id.";
	}

	@PostMapping(path = "/addRole/{role}")
	public String addRealmRole(@PathVariable(value = "role") String role) {
		service.addRealmRole(role);
		return "Role Added Successfully.";
	}
	
	@DeleteMapping(path = "/deleteRole/{role}")
	public String deleteRealmRole(@PathVariable(value = "role") String role) {
		service.deleteRealmRole(role);
		return "Role Deleted Successfully.";
	}

	@GetMapping(path = "/getAllRoles")
	public List<String> getAllRoles() {
		return service.getAllRoles();
	}
}
