package com.famisanar.req.helpers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.famisanar.req.dao.TicketRepository;
import com.famisanar.req.entities.Ticket;

@Service
public class TicketHelper {

    @Autowired
    private TicketRepository ticketRepository;
    
    //Consulta toda la tabla
    public List<Ticket> obtenerTicket(){
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets;
    }

    //Consulta por tema
    public List<Ticket> ticketPorTema(Integer tema){
        List<Ticket> tickets = ticketRepository.findByTema(tema);
        return tickets;
    }

    //Consulta por Gerencia
    public List<Ticket> ticketPorGerencia(Integer gerencia){
        List<Ticket> tickets = ticketRepository.findByGerencia(gerencia);
        return tickets;
    }

    //Consulta por Tiket
    public Ticket ticketSolo(String numTicket){
        Ticket ticket = ticketRepository.findByTicket(numTicket);
        return ticket;
    }

    //Consulta por fecha
    public List<Ticket> ticketPorFechaSol(Date fechaSol){
        List<Ticket> tickets = ticketRepository.findByFechaSol(fechaSol);
        return tickets;
    }

    //Consulta por estado TI
    public List<Ticket> ticketPorEstado(Integer estado){
        List<Ticket> tickets = ticketRepository.findByEstadoTI(estado);
        return tickets;
    }

    //Consulta por requerido
    public List<Ticket> ticketPorRequerido(Integer requerido){
        List<Ticket> tickets = ticketRepository.findByRequerido(requerido);
        return tickets;
    }

    public List<Ticket> ticketPorLey(Integer ley){
        List<Ticket> tickets = ticketRepository.findByDeLey(ley);
        return tickets;
    }

}
