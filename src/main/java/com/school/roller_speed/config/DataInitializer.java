package com.school.roller_speed.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class DataInitializer {
    
    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Bean
    CommandLineRunner init(JdbcTemplate jdbc, PasswordEncoder encoder) {
        return args -> {
            logger.info("=== [ROLLER-SPEED] Iniciando Inicialización de Datos ===");
            
            // 1. Validar e insertar Roles requeridos por Spring Security
            try {
                Integer countRoles = jdbc.queryForObject("SELECT COUNT(*) FROM roles_usuario", Integer.class);
                if (countRoles == null || countRoles == 0) {
                    logger.info("Poblando tabla: roles_usuario");
                    jdbc.update("INSERT INTO roles_usuario (nombre_rol) VALUES (?)", "ROLE_ADMIN");
                    jdbc.update("INSERT INTO roles_usuario (nombre_rol) VALUES (?)", "ROLE_DOCENTE");
                    jdbc.update("INSERT INTO roles_usuario (nombre_rol) VALUES (?)", "ROLE_ESTUDIANTE");
                } else {
                    // Aseguramos el prefijo ROLE_ por si acaso
                    jdbc.update("UPDATE roles_usuario SET nombre_rol = 'ROLE_ADMIN' WHERE nombre_rol = 'ADMIN' OR nombre_rol = 'Admin'");
                    jdbc.update("UPDATE roles_usuario SET nombre_rol = 'ROLE_DOCENTE' WHERE nombre_rol = 'DOCENTE' OR nombre_rol = 'Docente'");
                    jdbc.update("UPDATE roles_usuario SET nombre_rol = 'ROLE_ESTUDIANTE' WHERE nombre_rol = 'ESTUDIANTE' OR nombre_rol = 'Estudiante'");
                    logger.info("Roles estandarizados a Spring Security en la tabla roles_usuario.");
                }
            } catch (Exception e) {
                logger.error("Error procesando roles: " + e.getMessage());
            }

            // 2. Limpiar y recrear usuarios con contraseñas encriptadas reales (BCrypt)
            try {
                logger.info("Recreando usuarios con contraseñas encriptadas...");
                
                Long adminRolId = jdbc.queryForObject("SELECT id_rol FROM roles_usuario WHERE nombre_rol = ? LIMIT 1", Long.class, "ROLE_ADMIN");
                Long docenteRolId = jdbc.queryForObject("SELECT id_rol FROM roles_usuario WHERE nombre_rol = ? LIMIT 1", Long.class, "ROLE_DOCENTE");
                Long estudianteRolId = jdbc.queryForObject("SELECT id_rol FROM roles_usuario WHERE nombre_rol = ? LIMIT 1", Long.class, "ROLE_ESTUDIANTE");

                jdbc.update("SET FOREIGN_KEY_CHECKS = 0;");
                jdbc.update("TRUNCATE TABLE usuarios;");
                
                // Inserciones limpias usando tu encoder real
                jdbc.update("INSERT INTO usuarios (username, password, enabled, id_rol) VALUES (?, ?, ?, ?)", "admin1", encoder.encode("pass123"), true, adminRolId);
                jdbc.update("INSERT INTO usuarios (username, password, enabled, id_rol) VALUES (?, ?, ?, ?)", "docente1", encoder.encode("pass34"), true, docenteRolId);
                jdbc.update("INSERT INTO usuarios (username, password, enabled, id_rol) VALUES (?, ?, ?, ?)", "docente2", encoder.encode("pass56"), true, docenteRolId);
                jdbc.update("INSERT INTO usuarios (username, password, enabled, id_rol) VALUES (?, ?, ?, ?)", "estudiante1", encoder.encode("pass78"), true, estudianteRolId);
                jdbc.update("INSERT INTO usuarios (username, password, enabled, id_rol) VALUES (?, ?, ?, ?)", "estudiante2", encoder.encode("pass90"), true, estudianteRolId);
                jdbc.update("INSERT INTO usuarios (username, password, enabled, id_rol) VALUES (?, ?, ?, ?)", "estudiante3", encoder.encode("pass901"), true, estudianteRolId);
                jdbc.update("INSERT INTO usuarios (username, password, enabled, id_rol) VALUES (?, ?, ?, ?)", "estudiante4", encoder.encode("pass902"), true, estudianteRolId);
                jdbc.update("INSERT INTO usuarios (username, password, enabled, id_rol) VALUES (?, ?, ?, ?)", "estudiante5", encoder.encode("pass903"), true, estudianteRolId);
                
                logger.info("Usuarios creados exitosamente en la tabla 'usuarios'.");
            } catch (Exception e) {
                logger.error("Error al poblar la tabla usuarios: " + e.getMessage());
            }

            // 3. Re-vincular los id_usuario en docentes y estudiantes
            try {
                logger.info("Sincronizando llaves foráneas en docentes y estudiantes...");
                
                Long idD1 = jdbc.queryForObject("SELECT id_usuario FROM usuarios WHERE username = 'docente1'", Long.class);
                Long idD2 = jdbc.queryForObject("SELECT id_usuario FROM usuarios WHERE username = 'docente2'", Long.class);
                jdbc.update("UPDATE docentes SET id_usuario = ? WHERE nombre = 'Carlos' OR id_docente = 1", idD1);
                jdbc.update("UPDATE docentes SET id_usuario = ? WHERE nombre = 'María' OR id_docente = 2", idD2);

                Long u1 = jdbc.queryForObject("SELECT id_usuario FROM usuarios WHERE username = 'estudiante1'", Long.class);
                Long u2 = jdbc.queryForObject("SELECT id_usuario FROM usuarios WHERE username = 'estudiante2'", Long.class);
                Long u3 = jdbc.queryForObject("SELECT id_usuario FROM usuarios WHERE username = 'estudiante3'", Long.class);
                Long u4 = jdbc.queryForObject("SELECT id_usuario FROM usuarios WHERE username = 'estudiante4'", Long.class);
                Long u5 = jdbc.queryForObject("SELECT id_usuario FROM usuarios WHERE username = 'estudiante5'", Long.class);

                jdbc.update("UPDATE estudiantes SET id_usuario = ? WHERE nombre = 'Juan' OR id_estudiante = 1", u1);
                jdbc.update("UPDATE estudiantes SET id_usuario = ? WHERE nombre = 'Ana' OR id_estudiante = 2", u2);
                jdbc.update("UPDATE estudiantes SET id_usuario = ? WHERE nombre = 'Luis' OR id_estudiante = 3", u3);
                jdbc.update("UPDATE estudiantes SET id_usuario = ? WHERE nombre = 'Sofía' OR id_estudiante = 4", u4);
                jdbc.update("UPDATE estudiantes SET id_usuario = ? WHERE nombre = 'Diego' OR id_estudiante = 5", u5);

                jdbc.update("SET FOREIGN_KEY_CHECKS = 1;");
                logger.info("=== [ROLLER-SPEED] Inicialización Completada con Éxito ===");
            } catch (Exception e) {
                jdbc.update("SET FOREIGN_KEY_CHECKS = 1;");
                logger.error("Error al sincronizar llaves foráneas: " + e.getMessage());
            }
        };
    }
}