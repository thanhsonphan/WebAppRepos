package com.java.RateSystem.repository;

import com.java.RateSystem.models.Servicerate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<Servicerate, Long> {
    @Query("SELECT s FROM Servicerate s WHERE s.ServiceName = ?1")
    Optional<Servicerate> findByServiceName(String Name);
}
