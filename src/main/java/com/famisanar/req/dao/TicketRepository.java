package com.famisanar.req.dao;

import com.famisanar.req.entities.Ticket;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;


public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    
    List<Ticket> findByTema(Integer tema);

    List<Ticket> findByGerencia(Integer gerencia);

    List<Ticket> findByEstadoTI(Integer estadoTI);

    List<Ticket> findByFechaSol(Date fechaSol);

    Ticket findByTicket(String ticket);

    List<Ticket> findByRequerido(Integer requerido);

    List<Ticket> findByDeLey(Integer deLey);

}
