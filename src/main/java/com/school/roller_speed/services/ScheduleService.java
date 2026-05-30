package com.school.roller_speed.services;
import java.util.List;
import org.springframework.stereotype.Service;
import com.school.roller_speed.models.Schedule;
import com.school.roller_speed.repositories.ScheduleRepository;

@Service
public class ScheduleService {
     private final ScheduleRepository scheduleRepository;
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }
     // LISTAR USUARIOS
    public List<Schedule> getAllSchedules() {
    return scheduleRepository.findAll();
    }
    
}
