package com.healtracking.residuos.repository;

import com.healtracking.residuos.model.Residuo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResiduoRepository extends JpaRepository<Residuo, Long> {
    // Todos os métodos básicos prontos por padrão
}
