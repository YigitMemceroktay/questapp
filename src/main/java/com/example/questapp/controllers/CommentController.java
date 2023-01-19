package com.example.questapp.controllers;

import com.example.questapp.entities.Comment;
import com.example.questapp.repos.CommentRepository;
import com.example.questapp.requests.CommentCreateRequest;
import com.example.questapp.requests.CommentUpdateRequest;
import com.example.questapp.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getAllComments(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId) {
        return commentService.getAllComments(userId, postId);
    }

    @GetMapping("/{commentId}")
    public Comment getCommentById(@PathVariable Long commentId) {
        return commentService.getCommentById(commentId);
    }

    @PostMapping
    public Comment createComment(@RequestBody CommentCreateRequest commentCreateRequest) {
        return commentService.createComment(commentCreateRequest);
    }

    @PutMapping("/{postId}")
    public Comment updateComment(@PathVariable Long postId, @RequestBody CommentUpdateRequest commentUpdateRequest) {
        return commentService.updateComment(postId, commentUpdateRequest);
    }

    @DeleteMapping("/{postId}")
    public void deleteComment(@PathVariable Long postId){
         commentService.deleteComment(postId);
    }

}
