package com.famisanar.req.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.famisanar.req.entities.Caso;

public interface CasoRepository extends JpaRepository<Caso, Integer> {
    // @Query(value = "SELECT c FROM Caso c ", nativeQuery = false)
    // List<Caso> consultar();
}
