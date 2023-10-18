package com.famisanar.req.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.famisanar.req.entities.Actividad;

public interface ActividadRepository extends JpaRepository<Actividad, Integer>{
    
}
