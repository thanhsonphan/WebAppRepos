package com.java.RateSystem.repository;

import com.java.RateSystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT s FROM User s WHERE s.Email = ?1")
    Optional<User> findByEmail(String Email);
}
