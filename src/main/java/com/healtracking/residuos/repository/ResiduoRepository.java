package com.healtracking.residuos.repository;

import com.healtracking.residuos.model.Gerador;
import com.healtracking.residuos.model.Residuo;
import com.healtracking.residuos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResiduoRepository extends JpaRepository<Residuo, Long> {

    // Método para buscar apenas os resíduos criados por um utilizador específico
    List<Residuo> findByUsuario(Usuario usuario);
//checa se ta podendo excluir
    boolean existsByGerador(Gerador gerador);

}