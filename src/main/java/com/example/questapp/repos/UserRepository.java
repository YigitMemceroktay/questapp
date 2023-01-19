package com.example.questapp.repos;

import com.example.questapp.entities.Post;
import com.example.questapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

}
