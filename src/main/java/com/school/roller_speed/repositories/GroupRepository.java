package com.school.roller_speed.repositories;

import com.school.roller_speed.models.ClassGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<ClassGroup, Integer> {
}