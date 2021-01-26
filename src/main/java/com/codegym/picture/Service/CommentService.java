package com.codegym.picture.Service;

import com.codegym.picture.model.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findAll();

    void save(Comment comment);

    Comment update(Comment comment);

    Comment like(Long id);

    Comment findById(Long id);
}