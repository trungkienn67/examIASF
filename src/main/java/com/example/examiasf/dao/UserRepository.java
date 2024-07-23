package com.example.examiasf.dao;

import com.example.examiasf.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    // Add a method to sort by name in ascending order
    List<User> findAllByOrderByNameAsc();
}
