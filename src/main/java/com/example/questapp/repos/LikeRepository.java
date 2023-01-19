package com.example.questapp.repos;

import com.example.questapp.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {

    public List<Like> findByUserIdAndPostId(Long userId, Long postId);

    public List<Like> findByUserId(Long userId);

    public List<Like> findByPostId(Long postId);
}
