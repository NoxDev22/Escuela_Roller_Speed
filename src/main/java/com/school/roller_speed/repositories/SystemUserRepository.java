package com.school.roller_speed.repositories;

import com.school.roller_speed.models.SystemUser;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SystemUserRepository
        extends JpaRepository<SystemUser, Long> {
   @Query("SELECT u FROM SystemUser u WHERE u.userAssigned = false")
    List<SystemUser> findAvailableUsers();
}