package com.codegym.picture.controller;

import com.codegym.picture.Service.CommentService;
import com.codegym.picture.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Comment;

@Controller
@RequestMapping("home")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("comment", new Comment((short) 4, "@anhnbt", "CodeGym MonCity"));
        List<Comment> comments = commentService.findAll();
        modelAndView.addObject("comments", comments);
        return modelAndView;
    }

    @PostMapping
    public String save(Comment comment) {
        commentService.save(comment);
        return "redirect:/home";
    }

    @PostMapping(value = "like/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Comment> like(@PathVariable(name = "id") Long id) {
        Comment comment = commentService.like(id);
        System.out.println(comment.getLikeCount());
        if (comment == null) {
            return new ResponseEntity<>(comment, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

}