package com.famisanar.req.dao;

import com.famisanar.req.entities.Tema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemaRepository extends JpaRepository<Tema, Integer> {
}
