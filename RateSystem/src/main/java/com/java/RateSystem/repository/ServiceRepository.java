package com.java.RateSystem.repository;

import com.java.RateSystem.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.java.RateSystem.models.Services;

import java.util.List;
import java.util.Optional;
public interface ServiceRepository extends JpaRepository<Services, Integer> {
    @Query("SELECT s FROM Services s WHERE s.Id = ?1")
    List<Services> findByOtherId(Integer ServiceId);
}
