package com.famisanar.req.repositories;

import com.famisanar.req.entities.Caso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CasoRepository extends JpaRepository<Caso, Integer> {
}
