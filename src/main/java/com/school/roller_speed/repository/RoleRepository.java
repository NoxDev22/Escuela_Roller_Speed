package com.school.roller_speed.repository;

import com.school.roller_speed.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByNombreRol(String nombreRol);
}
