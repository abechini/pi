package com.esprit.bankPi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.esprit.bankPi.data.Topic;
import com.esprit.bankPi.resources.TopicService;

@Controller
@RequestMapping("/api/topics")
public class TopicController {
	@Autowired
	private TopicService topicService;

	@PostMapping(value = "/addTopic")
	public ResponseEntity addTopic(@RequestBody Topic topic) {
		Topic topicPostSave = null;
		try {
			topicPostSave = topicService.addTopic(topic);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(topicPostSave);
	}

	@PutMapping(value = "/updateTopic")
	public ResponseEntity updateTopic(@RequestBody Topic topic) {
		Topic topicPostSave = null;
		try {
			topicPostSave = topicService.updateTopic(topic);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(topicPostSave);
	}

	@DeleteMapping(value = "/deleteTopic/{id}")
	public ResponseEntity deleteTopic(@PathVariable Long id) {
		try {
			topicService.deleteTopic(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body("Topic deleted");
	}

	@GetMapping(value = "/findAllTopic")
	public ResponseEntity findAllTopic() {
		List<Topic> topicList = new ArrayList<>();
		try {
			topicList = topicService.findAllTopic();
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(topicList);
	}

	@GetMapping(value = "/findTopic/{id}")
	public ResponseEntity findTopic(@PathVariable Long id) {
		Topic topic = null;
		try {
			topic = topicService.findTopicById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body("Topic not found");
		}
		return ResponseEntity.status(HttpStatus.OK).body(topic);
	}

	@PostMapping(value = "/increaseLikes/{id}")
	public ResponseEntity increaseLikes(@PathVariable Long id) {
		int nbrOfLikes = topicService.increaseLikes(id);
		return ResponseEntity.status(HttpStatus.OK).body(nbrOfLikes);
	}

	@PostMapping(value = "/decreaseLikes/{id}")
	public ResponseEntity decreaseLikes(@PathVariable Long id) {
		int nbrOfLikes = topicService.decreaseLikes(id);
		return ResponseEntity.status(HttpStatus.OK).body(nbrOfLikes);
	}
}
