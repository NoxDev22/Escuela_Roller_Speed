package com.school.roller_speed.repository;

import com.school.roller_speed.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
	Optional<Estudiante> findByUsuario_Username(String username);
}
