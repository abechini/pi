package com.esprit.bankPi.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.bankPi.data.Comment;
import com.esprit.bankPi.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentRepository commentRepository;

	@Override
	public Comment addComment(Comment comment) {
		return commentRepository.save(comment);
	}

	@Override
	public Comment updateComment(Comment comment) {
		return commentRepository.save(comment);
	}

	@Override
	public void deleteComment(Long id) {
		commentRepository.deleteById(id);
	}

	@Override
	public List<Comment> findAllComments() {
		return (List<Comment>) commentRepository.findAll();
	}

	@Override
	public Comment findCommentById(Long id) {
		return commentRepository.findById(id).get();
	}

}
