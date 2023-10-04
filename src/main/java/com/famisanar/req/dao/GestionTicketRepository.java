package com.famisanar.req.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.famisanar.req.entities.GestionTicket;

public interface GestionTicketRepository extends JpaRepository<GestionTicket, Integer>{
     List<GestionTicket> findByTicketId(Integer ticketId);
}
