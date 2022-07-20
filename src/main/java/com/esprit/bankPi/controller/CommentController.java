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

import com.esprit.bankPi.data.Comment;
import com.esprit.bankPi.data.Topic;
import com.esprit.bankPi.resources.CommentService;
import com.esprit.bankPi.resources.TopicService;

@Controller
@RequestMapping("/api/comments")
public class CommentController {
	@Autowired
	private CommentService commentService;
	@Autowired
	private TopicService topicService;

	@PostMapping(value = "/addComment/{idTopic}")
	public ResponseEntity addComment(@RequestBody Comment comment, @PathVariable Long idTopic) {
		Comment commentPostSave = null;
		Topic topic = null;
		try {
			topic = topicService.findTopicById(idTopic);
			comment.setTopic(topic);
			commentPostSave = commentService.addComment(comment);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body("Topic not found");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(commentPostSave);
	}

	@PutMapping(value = "/updateComment")
	public ResponseEntity updateComment(@RequestBody Comment comment) {
		Comment commentPostSave = null;
		Topic Topic = null;
		try {
			Topic = commentService.findCommentById(comment.getId()).getTopic();
			comment.setTopic(Topic);
			commentPostSave = commentService.updateComment(comment);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(commentPostSave);
	}

	@DeleteMapping(value = "/deleteComment/{id}")
	public ResponseEntity deleteComment(@PathVariable Long id) {
		try {
			commentService.deleteComment(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body("Comment deleted");
	}

	@GetMapping(value = "/findAllCommentsByTopic/{idTopic}")
	public ResponseEntity findAllComments(@PathVariable Long idTopic) {
		List<Comment> commentList = new ArrayList<>();
		Topic topic = null;
		try {
			topic = topicService.findTopicById(idTopic);
			commentList = topic.getComments();
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(commentList);
	}

	@GetMapping(value = "/findComment/{id}")
	public ResponseEntity findComment(@PathVariable Long id) {
		Comment comment = null;
		try {
			comment = commentService.findCommentById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(comment);
	}
}
