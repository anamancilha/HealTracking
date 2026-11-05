package com.healtracking.residuos.repository;

import com.healtracking.residuos.model.Transportador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportadorRepository extends JpaRepository<Transportador, Long> {
}

