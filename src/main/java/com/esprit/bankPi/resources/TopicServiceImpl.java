package com.esprit.bankPi.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.bankPi.data.Topic;
import com.esprit.bankPi.repository.TopicRepository;

@Service
public class TopicServiceImpl implements TopicService {
	@Autowired
	private TopicRepository topicRepository;

	@Override
	public Topic addTopic(Topic topic) {
		return topicRepository.save(topic);
	}

	@Override
	public Topic updateTopic(Topic topic) {
		return topicRepository.save(topic);
	}

	@Override
	public void deleteTopic(Long id) {
		topicRepository.deleteById(id);
	}

	@Override
	public List<Topic> findAllTopic() {
		return (List<Topic>) topicRepository.findAll();
	}

	@Override
	public Topic findTopicById(Long id) {
		return topicRepository.findById(id).get();
	}

	@Override
	public int increaseLikes(Long id) {
		Topic topic = findTopicById(id);
		topic.setNbrOfLikes(topic.getNbrOfLikes() + 1);
		topicRepository.save(topic);
		return topic.getNbrOfLikes();
	}
	
	@Override
	public int decreaseLikes(Long id) {
		Topic topic = findTopicById(id);
		topic.setNbrOfLikes(topic.getNbrOfLikes() - 1);
		topicRepository.save(topic);
		return topic.getNbrOfLikes();
	}

}
