package com.school.roller_speed.service;

import com.school.roller_speed.model.Usuario;
import com.school.roller_speed.repository.UsuarioRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class DatabaseUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public DatabaseUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscamos el usuario en la tabla 'usuarios'
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        // Obtenemos el nombre del rol guardado (que ya viene como 'ROLE_ESTUDIANTE', 'ROLE_ADMIN', etc.)
        String roleName = usuario.getRole() != null ? usuario.getRole().getNombreRol() : "ROLE_ESTUDIANTE";
        
        // Creamos la autoridad directamente asegurando que esté en mayúsculas y sin duplicar el prefijo 'ROLE_'
        String finalRole = roleName.toUpperCase().startsWith("ROLE_") ? roleName.toUpperCase() : "ROLE_" + roleName.toUpperCase();
        GrantedAuthority authority = new SimpleGrantedAuthority(finalRole);

        return new org.springframework.security.core.userdetails.User(
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.isEnabled(),
                true,
                true,
                true,
                List.of(authority)
        );
    }
}