package com.esprit.bankPi.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.core.Response;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
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
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setEmail(userDTO.getEmailId());
		user.setCredentials(Collections.singletonList(credential));
		user.setEnabled(true);
		UsersResource instance = getInstance();
		Response response = instance.create(user);
		assignRoleToUser(userDTO.getUserName(), userDTO.getRole());
		System.out.println(response);
		userRepository.save(userDTO);
	}

	private void assignRoleToUser(String userName, String role) {
		Keycloak keycloak = Keycloak.getInstance("http://localhost:8180/auth", "pi", "admin1", "admin1", "pi-app");
		String userId = keycloak.realm("pi").users().search(userName).get(0).getId();
		UserResource user = keycloak.realm("pi").users().get(userId);
		List<RoleRepresentation> roleToAdd = new LinkedList<>();
		roleToAdd.add(keycloak.realm("pi").roles().get(role).toRepresentation());
		user.roles().realmLevel().add(roleToAdd);
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
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setEmail(userDTO.getEmailId());
		user.setCredentials(Collections.singletonList(credential));

		UsersResource usersResource = getInstance();
		usersResource.get(userId).update(user);
		userRepository.save(userDTO);
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

	public List<String> getAllRoles() {
		Keycloak keycloak = Keycloak.getInstance("http://localhost:8180/auth", "pi", "admin1", "admin1", "pi-app");
		List<String> availableRoles = keycloak.realm("pi").roles().list().stream().map(role -> role.getName())
				.collect(Collectors.toList());
		return availableRoles;
	}

	public void addRealmRole(String new_role_name) {
		Keycloak keycloak = Keycloak.getInstance("http://localhost:8180/auth", "pi", "admin1", "admin1", "pi-app");
		if (!getAllRoles().contains(new_role_name)) {
			RoleRepresentation roleRep = new RoleRepresentation();
			roleRep.setName(new_role_name);
			roleRep.setDescription("role_" + new_role_name);
			keycloak.realm("pi").roles().create(roleRep);
		}
	}

}
