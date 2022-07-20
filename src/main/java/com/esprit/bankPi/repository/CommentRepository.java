package com.esprit.bankPi.repository;

import org.springframework.data.repository.CrudRepository;

import com.esprit.bankPi.data.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {

}
