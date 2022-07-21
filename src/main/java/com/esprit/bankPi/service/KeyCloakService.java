package com.esprit.bankPi.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.core.Response;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.bankPi.data.UserDTO;
import com.esprit.bankPi.keycloak.Credentials;
import com.esprit.bankPi.repository.UserRepository;

@Service
public class KeyCloakService {
	@Autowired
	UserRepository userRepository;

	public void addUser(UserDTO userDTO) {
		CredentialRepresentation credential = Credentials.createPasswordCredentials(userDTO.getPassword());
		UserRepresentation user = new UserRepresentation();
		user.setUsername(userDTO.getUserName());
		user.setFirstName(userDTO.getFirstname());
		user.setLastName(userDTO.getLastName());
		user.setEmail(userDTO.getEmailId());
		user.setCredentials(Collections.singletonList(credential));
		user.setEnabled(true);

		UsersResource instance = getInstance();
		Response response = instance.create(user);
		System.out.println(response);
	}

	public List<UserRepresentation> getUser(String userName) {
		UsersResource usersResource = getInstance();
		List<UserRepresentation> user = usersResource.search(userName, true);
		return user;

	}

	public void updateUser(String userId, UserDTO userDTO) {
		CredentialRepresentation credential = Credentials.createPasswordCredentials(userDTO.getPassword());
		UserRepresentation user = new UserRepresentation();
		user.setUsername(userDTO.getUserName());
		user.setFirstName(userDTO.getFirstname());
		user.setLastName(userDTO.getLastName());
		user.setEmail(userDTO.getEmailId());
		user.setCredentials(Collections.singletonList(credential));

		UsersResource usersResource = getInstance();
		usersResource.get(userId).update(user);
	}

	public void deleteUser(String userId) {
		UsersResource usersResource = getInstance();
		usersResource.get(userId).remove();
	}

	public void sendVerificationLink(String userName) {
		List<UserRepresentation> userRepresenation = getUser(userName);
		String userId = userRepresenation.get(0).getId();
		UsersResource usersResource = getInstance();
		usersResource.get(userId).sendVerifyEmail();
	}

	public void sendResetPassword(String userName) {
		List<UserRepresentation> userRepresenation = getUser(userName);
		String userId = userRepresenation.get(0).getId();
		UsersResource usersResource = getInstance();
		usersResource.get(userId).executeActionsEmail(Arrays.asList("UPDATE_PASSWORD"));
	}

	public UsersResource getInstance() {
		Keycloak keycloak = Keycloak.getInstance("http://localhost:8180/auth", "pi", "admin1", "admin1", "pi-app");
//		RealmRepresentation realm = keycloak.realm("pi").toRepresentation();
		RealmResource realmResource = keycloak.realm("pi");
		return realmResource.users();
	}

	public void persistKeycloakUsers() {
		List<UserRepresentation> users = getInstance().list();
		for (UserRepresentation user : users) {
			UserDTO userDTO = new UserDTO();
			userDTO.setUserName(user.getUsername());
			userDTO.setEmailId(user.getEmail());
			userDTO.setFirstname(user.getFirstName());
			userDTO.setLastName(user.getLastName());
			userDTO.setPassword("keycloak");
			userRepository.save(userDTO);
		}
	}
}
