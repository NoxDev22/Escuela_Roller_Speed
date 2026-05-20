package com.school.roller_speed.repositories;

import com.school.roller_speed.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository
        extends JpaRepository<UserRole, Long> {

}