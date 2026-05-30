package com.school.roller_speed.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.school.roller_speed.models.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer>{
  
    
} 
