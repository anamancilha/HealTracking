package com.healtracking.residuos.repository;

import com.healtracking.residuos.model.TipoResiduo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface TipoResiduoRepository extends JpaRepository<TipoResiduo, Long> {
}