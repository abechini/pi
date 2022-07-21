package com.esprit.bankPi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.esprit.bankPi.data.Topic;

@Repository
public interface TopicRepository extends CrudRepository<Topic, Long> {

}
