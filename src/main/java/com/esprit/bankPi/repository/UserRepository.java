package com.esprit.bankPi.repository;

import org.springframework.data.repository.CrudRepository;

import com.esprit.bankPi.data.UserDTO;

public interface UserRepository extends CrudRepository<UserDTO, Long> {

}
