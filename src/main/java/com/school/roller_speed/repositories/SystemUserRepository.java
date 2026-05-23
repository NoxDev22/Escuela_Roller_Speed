package com.school.roller_speed.repositories;

import com.school.roller_speed.models.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemUserRepository
        extends JpaRepository<SystemUser, Long> {

}