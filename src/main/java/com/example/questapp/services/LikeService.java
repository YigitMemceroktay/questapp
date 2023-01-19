package com.example.questapp.services;

import com.example.questapp.entities.Like;
import com.example.questapp.entities.Post;
import com.example.questapp.entities.User;
import com.example.questapp.repos.LikeRepository;
import com.example.questapp.requests.LikeCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {

    private LikeRepository likeRepository;

    private UserService userService;

    private PostService postService;


    public LikeService(LikeRepository likeRepository, UserService userService, PostService postService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<Like> getLikes(Optional<Long> userId, Optional<Long> postId) {
        if(userId.isPresent() && postId.isPresent()) {
          return likeRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if(userId.isPresent()) {
            return likeRepository.findByUserId(userId.get());
        } else if(postId.isPresent()) {
            return likeRepository.findByPostId(postId.get());
        } else {
            return likeRepository.findAll();
        }


    }
    public Like getLike(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

    public Like createLike(LikeCreateRequest likeCreateRequest){
        User user = userService.findByUserId(likeCreateRequest.getUserId());
        Post post = postService.getPost(likeCreateRequest.getPostId());
        if(user != null && post!= null){
            Like like = new Like();
            like.setId(likeCreateRequest.getId());
            like.setUser(user);
            like.setPost(post);
            return likeRepository.save(like);
        }
        return null;
    }

    public void deleteLike(Long likeId) {
        likeRepository.deleteById(likeId);
    }
}
