package com.famisanar.req.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.famisanar.req.entities.Persona;

public interface ResponsableRepository extends JpaRepository<Persona, Integer>{
    @Query(value = "SELECT p FROM Persona p "
                    + "WHERE p.actividad = 2 "
                    + "AND p.estado <> 2 ", nativeQuery = false)
    List<Persona>personasResponsable();

    @Query(value = "SELECT p FROM Persona p "
                    + "WHERE p.actividad = 1 "
                    + "AND p.estado <> 2 ", nativeQuery = false)
    List<Persona>personassolicitante();

    @Query(value = "SELECT p FROM Persona p "
                    + "WHERE p.numeroIdentificacion = ?1", nativeQuery = false )
    Persona personaCedula(String cedula);

}
