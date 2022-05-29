package com.java.RateSystem.repository;

import com.java.RateSystem.models.Rating;
//import com.sun.xml.bind.v2.schemagen.xmlschema.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;
public interface RatingRespository extends JpaRepository<Rating, UUID>{
    @Query("SELECT s FROM Rating s WHERE s.ServiceId = ?1")
    List<Rating> findByServiceId(Integer ServiceId);
}
