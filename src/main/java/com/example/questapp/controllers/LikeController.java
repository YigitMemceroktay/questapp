package com.example.questapp.controllers;

import com.example.questapp.entities.Like;
import com.example.questapp.requests.LikeCreateRequest;
import com.example.questapp.services.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/likes")
public class LikeController {

    private LikeService likeSerivce;

    public LikeController(LikeService likeService) {
        this.likeSerivce = likeService;
    }



    @GetMapping
    public List<Like> getLikes(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId) {
        return likeSerivce.getLikes(userId, postId);
    }

    @GetMapping("/{likeId}")
    public Like getLike(@PathVariable Long likeId) {
        return likeSerivce.getLike(likeId);
    }

    @PostMapping
    public Like postLike(@RequestBody LikeCreateRequest likeCreateRequest) {
        return likeSerivce.createLike(likeCreateRequest);
    }

    @DeleteMapping("/{likeId}")
    public void deleteLike(@PathVariable Long likeId) {
        likeSerivce.deleteLike(likeId);
    }

}
