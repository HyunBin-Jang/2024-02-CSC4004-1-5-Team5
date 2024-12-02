package com.travelkit.backend.Repository;

import com.travelkit.backend.domain.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WeatherRepository extends JpaRepository<Weather, Long> {
    @Query("SELECT w FROM Weather w WHERE w.checklist.id = :checklistId")
    List<Weather> findByChecklistId(@Param("checklistId") Long checklistId);
}
