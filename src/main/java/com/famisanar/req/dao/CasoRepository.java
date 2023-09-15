package com.famisanar.req.dao;

import com.famisanar.req.entities.Caso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CasoRepository extends JpaRepository<Caso, Integer> {
}
