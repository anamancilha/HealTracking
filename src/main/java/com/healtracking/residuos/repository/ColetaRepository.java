package com.healtracking.residuos.repository;

import com.healtracking.residuos.model.Coleta;
import com.healtracking.residuos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColetaRepository extends JpaRepository<Coleta, Long> {

    // Filtra as coletas pertencentes a um utilizador específico (para o utilizador comum)
    List<Coleta> findByUsuario(Usuario usuario);
}