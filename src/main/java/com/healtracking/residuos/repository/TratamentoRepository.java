package com.healtracking.residuos.repository;

import com.healtracking.residuos.model.Tratamento;
import com.healtracking.residuos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TratamentoRepository extends JpaRepository<Tratamento, Long> {

    // Filtra os tratamentos pertencentes a um utilizador específico
    List<Tratamento> findByUsuario(Usuario usuario);
}