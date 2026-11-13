package com.healtracking.residuos.repository;

import com.healtracking.residuos.model.Gerador;
import com.healtracking.residuos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeradorRepository extends JpaRepository<Gerador, Long> {

    // Adiciona esta linha para permitir filtrar os geradores por utilizador
    List<Gerador> findByUsuario(Usuario usuario);
}