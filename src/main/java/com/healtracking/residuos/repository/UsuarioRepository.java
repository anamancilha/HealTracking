package com.healtracking.residuos.repository;

import com.healtracking.residuos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // O Spring Data gera automaticamente o SQL de busca por e-mail apenas por causa do nome do método!
    Optional<Usuario> findByEmail(String email);
}
