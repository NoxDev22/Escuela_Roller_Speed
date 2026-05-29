package com.school.roller_speed.repositories;

import com.school.roller_speed.models.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface SystemUserRepository extends JpaRepository<SystemUser, Long> {

    @Query("SELECT u FROM SystemUser u WHERE u.userAssigned = false")
    List<SystemUser> findAvailableUsers();
}