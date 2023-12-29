package com.example.airlinemanagementsolution.repositories;

import com.example.airlinemanagementsolution.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
