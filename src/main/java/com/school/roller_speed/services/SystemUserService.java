package com.school.roller_speed.services;

import com.school.roller_speed.models.SystemUser;
import com.school.roller_speed.repositories.SystemUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service public class SystemUserService {

@Autowired private SystemUserRepository userRepository;

// LISTAR USUARIOS

public List<SystemUser> listarUsuarios() {
    return userRepository.findAll();
    }

// GUARDAR USUARIO

public void guardarUsuario(SystemUser user) {
    userRepository.save(user);
    }

// BUSCAR POR ID

public SystemUser buscarPorId(Long id) {
    Optional<SystemUser> optional =
    userRepository.findById(id);

    return optional.orElse(null);
    }

// ELIMINAR

public void eliminarUsuario(Long id) {

    userRepository.deleteById(id);

    }

}



