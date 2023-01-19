package com.example.questapp.services;

import com.example.questapp.entities.Comment;
import com.example.questapp.entities.Post;
import com.example.questapp.entities.User;
import com.example.questapp.repos.CommentRepository;
import com.example.questapp.requests.CommentCreateRequest;
import com.example.questapp.requests.CommentUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    private UserService userService;

    private PostService postService;

    public CommentService(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }
    public List<Comment> getAllComments(Optional<Long> userId, Optional<Long> postId) {
        if(userId.isPresent() && postId.isPresent()) {
            return commentRepository.findByPostIdAndUserId(postId.get(), userId.get());
        } else if(userId.isPresent()) {
            return commentRepository.findByUserId(userId.get());
        } else if(postId.isPresent()) {
            return commentRepository.findByPostId(postId.get());
        }
        return commentRepository.findAll();

    }

    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createComment(CommentCreateRequest commentCreateRequest) {
        User user = userService.findByUserId(commentCreateRequest.getUserId());
        Post post = postService.getPost(commentCreateRequest.getPostId());

        if(user != null && post != null) {
            Comment toSave = new Comment();
            toSave.setId(commentCreateRequest.getId());
            toSave.setUser(user);
            toSave.setPost(post);
            toSave.setText(commentCreateRequest.getText());
            return commentRepository.save(toSave);
        }
        return null;
    }


    public Comment updateComment(Long postId, CommentUpdateRequest commentUpdateRequest) {
        Optional<Comment> comment = commentRepository.findById(postId);
        if(comment.isPresent()) {
            Comment toUpdate = comment.get();
            toUpdate.setText(commentUpdateRequest.getText());
            return commentRepository.save(toUpdate);
        }
        return null;
    }

    public void deleteComment(Long postId) {
        commentRepository.deleteById(postId);
    }
}
