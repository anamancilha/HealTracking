package com.healtracking.residuos.repository;

import com.healtracking.residuos.model.Transportador;
import com.healtracking.residuos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransportadorRepository extends JpaRepository<Transportador, Long> {
    List<Transportador> findByUsuario(Usuario usuario);
}