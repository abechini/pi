package com.esprit.bankPi.resources;

import java.util.List;

import com.esprit.bankPi.data.Topic;

public interface TopicService {
	Topic addTopic(Topic topic);

	Topic updateTopic(Topic topic);

	void deleteTopic(Long id);

	List<Topic> findAllTopic();

	Topic findTopicById(Long id);
}
