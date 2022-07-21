package com.esprit.bankPi.resources;

import java.util.List;

import com.esprit.bankPi.data.Comment;


public interface CommentService {
	Comment addComment(Comment comment);

	Comment updateComment(Comment comment);

	void deleteComment(Long id);

	List<Comment> findAllComments();

	Comment findCommentById(Long id);
}
