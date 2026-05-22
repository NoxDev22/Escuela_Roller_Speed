-- Schema and seed data for roller_speed database
CREATE DATABASE IF NOT EXISTS roller_speed;
USE roller_speed;

-- Create tables with correct naming for Spring Boot JPA entities
CREATE TABLE IF NOT EXISTS roles_usuario (
    id_rol BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre_rol VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS usuarios (
    id_usuario BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN DEFAULT TRUE,
    id_rol BIGINT,
    FOREIGN KEY (id_rol) REFERENCES roles_usuario(id_rol)
);

CREATE TABLE IF NOT EXISTS docentes (
    id_docente BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50),
    apellidos VARCHAR(50),
    correo VARCHAR(100),
    id_usuario BIGINT,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);

CREATE TABLE IF NOT EXISTS horarios_clases (
    id_horario BIGINT PRIMARY KEY AUTO_INCREMENT,
    dia VARCHAR(50),
    hora VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS grupos (
    id_grupo BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre_grupo VARCHAR(100),
    id_docente BIGINT,
    id_horario BIGINT,
    FOREIGN KEY (id_docente) REFERENCES docentes(id_docente),
    FOREIGN KEY (id_horario) REFERENCES horarios_clases(id_horario)
);

CREATE TABLE IF NOT EXISTS horario_grupo (
    id_horariogrupo BIGINT PRIMARY KEY AUTO_INCREMENT,
    id_grupo BIGINT,
    id_horario BIGINT,
    FOREIGN KEY (id_grupo) REFERENCES grupos(id_grupo),
    FOREIGN KEY (id_horario) REFERENCES horarios_clases(id_horario)
);

CREATE TABLE IF NOT EXISTS estudiantes (
    id_estudiante BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50),
    apellidos VARCHAR(50),
    id_grupo BIGINT,
    id_usuario BIGINT,
    FOREIGN KEY (id_grupo) REFERENCES grupos(id_grupo),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);

-- Insert roles
INSERT INTO roles_usuario (nombre_rol) VALUES
('ADMIN'),
('DOCENTE'),
('ESTUDIANTE');

-- Insert users with encrypted passwords (these are hashed with BCrypt)
-- NOTE: In real app, use proper password encoding. These are example hashes:
-- admin1/pass123: $2a$10$SlVZQkVVSldVSldVSldVS.
-- docente1/pass34: $2a$10$SlVZQkVVSldVSldVSldVS.
-- Students/pass78: $2a$10$SlVZQkVVSldVSldVSldVS.
INSERT INTO usuarios (username, password, enabled, id_rol) VALUES
('admin1','$2a$10$Xvz8J/Kj2C.pWzR7qN8f1eZvZ5ZZZ5ZZZ5ZZZ5ZZZ5ZZZ5ZZZ5ZZZ',TRUE,1),
('docente1','$2a$10$Xvz8J/Kj2C.pWzR7qN8f1eZvZ5ZZZ5ZZZ5ZZZ5ZZZ5ZZZ5ZZZ5ZZZ',TRUE,2),
('docente2','$2a$10$Xvz8J/Kj2C.pWzR7qN8f1eZvZ5ZZZ5ZZZ5ZZZ5ZZZ5ZZZ5ZZZ5ZZZ',TRUE,2),
('estudiante1','$2a$10$Xvz8J/Kj2C.pWzR7qN8f1eZvZ5ZZZ5ZZZ5ZZZ5ZZZ5ZZZ5ZZZ5ZZZ',TRUE,3),
('estudiante2','$2a$10$Xvz8J/Kj2C.pWzR7qN8f1eZvZ5ZZZ5ZZZ5ZZZ5ZZZ5ZZZ5ZZZ5ZZZ',TRUE,3),
('estudiante3','$2a$10$Xvz8J/Kj2C.pWzR7qN8f1eZvZ5ZZZ5ZZZ5ZZZ5ZZZ5ZZZ5ZZZ5ZZZ',TRUE,3),
('estudiante4','$2a$10$Xvz8J/Kj2C.pWzR7qN8f1eZvZ5ZZZ5ZZZ5ZZZ5ZZZ5ZZZ5ZZZ5ZZZ',TRUE,3),
('estudiante5','$2a$10$Xvz8J/Kj2C.pWzR7qN8f1eZvZ5ZZZ5ZZZ5ZZZ5ZZZ5ZZZ5ZZZ5ZZZ',TRUE,3);

-- Insert docentes (teachers)
INSERT INTO docentes (nombre, apellidos, correo, id_usuario) VALUES
('Carlos','Pérez','carlos.perez@rollerspeed.com',2),
('María','Rodríguez','maria.rodriguez@rollerspeed.com',3);

-- Insert horarios (schedules)
INSERT INTO horarios_clases (dia, hora) VALUES
('Lunes','09:00 - 10:30'),
('Martes','14:00 - 15:30'),
('Miércoles','16:00 - 17:30'),
('Jueves','09:00 - 10:30'),
('Viernes','14:00 - 15:30');

-- Insert grupos (groups) - linked to docentes and first horario
INSERT INTO grupos (nombre_grupo, id_docente, id_horario) VALUES
('GAMA0126',1,1),
('BETA0126',2,2);

-- Insert horario_grupo (group schedules)
INSERT INTO horario_grupo (id_grupo, id_horario) VALUES
(1,1),
(1,3),
(2,2),
(2,4);

-- Insert estudiantes (students) - linked to usuarios and grupos
INSERT INTO estudiantes (nombre, apellidos, id_grupo, id_usuario) VALUES
('Juan','Martínez',1,4),
('Ana','García',1,5),
('Luis','Ramírez',1,6),
('Sofía','Hernández',2,7),
('Diego','Suárez',2,8);

