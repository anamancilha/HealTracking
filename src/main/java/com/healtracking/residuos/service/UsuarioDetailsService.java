package com.healtracking.residuos.service;

import com.healtracking.residuos.model.Usuario;
import com.healtracking.residuos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Busca o usuário no banco pelo e-mail digitado no login
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));

        // Entrega os dados e o perfil (Role) para o Spring Security validar
        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getSenha())
                .roles(usuario.getPerfil()) 
                .build();
    }
}

