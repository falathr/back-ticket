package com.famisanar.req.helpers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.famisanar.req.dao.TicketRepository;
import com.famisanar.req.entities.Ticket;
import com.famisanar.req.request.TicketPorFiltroRequest;
import com.famisanar.req.request.TicketRequest;
import com.famisanar.req.request.TicketUpdateRequest;
import com.famisanar.req.request.TicketDatosFiltros;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class TicketHelper {

    private final static Logger logger = Logger.getLogger(TicketHelper.class.getName());

    @Autowired
    private TicketRepository ticketRepository;

    @PersistenceContext
    EntityManager manager;

    // Consulta toda la tabla
    public List<Ticket> obtenerTicket() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets;
    }

    // Consulta por tema
    public List<Ticket> ticketPorTema(Integer tema) {
        List<Ticket> tickets = ticketRepository.findByTema(tema);
        return tickets;
    }

    // Consulta por Gerencia
    public List<Ticket> ticketPorGerencia(Integer gerencia) {
        List<Ticket> tickets = ticketRepository.findByGerencia(gerencia);
        return tickets;
    }

    // Consulta por Tiket
    public Ticket ticketSolo(String numTicket) {
        Ticket ticket = ticketRepository.findByTicket(numTicket);
        return ticket;
    }

    // Consulta por fecha
    public List<Ticket> ticketPorFechaSol(Date fechaSol) {
        List<Ticket> tickets = ticketRepository.findByFechaSol(fechaSol);
        return tickets;
    }

    // Consulta por estado TI
    public List<Ticket> ticketPorEstado(Integer estado) {
        List<Ticket> tickets = ticketRepository.findByEstadoTI(estado);
        return tickets;
    }

    // Consulta por requerido
    public List<Ticket> ticketPorRequerido(Integer requerido) {
        List<Ticket> tickets = ticketRepository.findByRequerido(requerido);
        return tickets;
    }

    // Consulta por requerido
    public List<Ticket> ticketPorLey(Integer ley) {
        List<Ticket> tickets = ticketRepository.findByDeLey(ley);
        return tickets;
    }

    // Guarda un nuevo ticket
    public boolean guardarTicket(TicketRequest ticket) {
        Ticket ticket2 = new Ticket();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = dateFormat.parse(ticket.getFechaSol());
            ticket2.setTipo(Integer.parseInt(ticket.getTipo()));
            ticket2.setTicket(ticket.getTicket());
            ticket2.setTema(Integer.parseInt(ticket.getTema()));
            ticket2.setDescricion(ticket.getDescripcion());
            ticket2.setSolicitante(ticket.getSolicitante());
            ticket2.setGerencia(Integer.parseInt(ticket.getGerencia()));
            ticket2.setFechaSol(date);
            ticket2.setResponsable(ticket.getResponsable());
            ticket2.setEstadoTI(Integer.parseInt(ticket.getEstadoTI()));
            ticket2.setRequerido(Integer.parseInt(ticket.getRequerido()));
            ticket2.setDeLey(Integer.parseInt(ticket.getDeLey()));
            ticket2.setObservaciones(ticket.getObservaciones());

            ticketRepository.save(ticket2);
            return true;
        } catch (Exception e) {
            logger.info("Error: " + e.getCause());
            logger.info("Error: " + e.getMessage());
            return false;
        }
    }

    //Metodo principal para consultar por filtro o sin filtro
    public List<Ticket> consultaConFiltros(TicketDatosFiltros request) {
        String query = "SELECT t FROM Ticket t WHERE ";
        List<TicketPorFiltroRequest> filtroRequests = request.getDatos();
        List<Ticket> list = new ArrayList<>();
        try {
            //Valida si la variable viene con o sin filtros
            if (filtroRequests.size() == 0) {
                //realiza la consulta sin filtros
                list = obtenerTicket();
            } else {
                for (int i = 0; i < filtroRequests.size(); i++) {
                    //Arma la consulta con filtros
                    if (i > 0) {
                        query += " AND ";
                    }
                    TicketPorFiltroRequest datosFinal = filtroRequests.get(i);
                    String valor = datosFinal.getValor();

                    switch (datosFinal.getId()) {
                        case "1":
                            query += " t.tipo = " + valor.trim();
                            break;
                        case "2":
                            query += " t.ticket like '%" + valor.trim() + "%'";
                            break;
                        case "3":
                            query += " t.tema = " + valor.trim();
                            break;
                        case "4":
                            query += " t.descricion like '%" + valor.trim() + "%'";
                            break;
                        case "5":
                            query += " t.solicitante like '%" + valor.trim() + "%'";
                            break;
                        case "6":
                            query += " t.gerencia = " + valor.trim();
                            break;
                        case "7":
                            query += " t.fecha_sol = " + valor.trim();
                            break;
                        case "8":
                            query += " t.responsable like '%" + valor.trim() + "%'";
                            break;
                        case "9":
                            query += " t.estado_ti = " + valor.trim();
                            break;
                        case "10":
                            query += " t.requerido = " + valor.trim();
                            break;
                        case "11":
                            query += " t.de_ley = " + valor.trim();
                            break;
                        case "12":
                            query += " t.observaciones like '%" + valor.trim() + "%'";
                            break;
                        default:
                            break;
                    }

                }
                //Realiza la consulta con filtros aplicados
                list = manager.createQuery(query, Ticket.class).getResultList();
            }

        } catch (Exception e) {
            logger.info("Error: " + e.getCause());
            logger.info("Error: " + e.getMessage());
        }
        //Retorna la lista de la consulta
        return list;
    }

    public Ticket actualizarTicket(Integer id, TicketUpdateRequest request ){
        Optional<Ticket> ticketActual = ticketRepository.findById(id);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Ticket ticketActualizado = new Ticket();
        try {
            if (ticketActual.isPresent()) {
                Ticket nuevoTicket = ticketActual.get();
                if (request.getFechaSol()!= null) {
                    Date date = dateFormat.parse(request.getFechaSol());
                    nuevoTicket.setFechaSol(date);
                }else{
                    nuevoTicket.setFechaSol(nuevoTicket.getFechaSol());
                }
                nuevoTicket.setDeLey(request.getDeLey() != null ? Integer.parseInt(request.getDeLey()) : nuevoTicket.getDeLey());
                nuevoTicket.setDescricion(request.getDescripcion() != null ? request.getDescripcion() : nuevoTicket.getDescricion());
                nuevoTicket.setEstadoTI(request.getEstadoTI() != null ? Integer.parseInt(request.getEstadoTI()) : nuevoTicket.getEstadoTI());               
                nuevoTicket.setGerencia(request.getGerencia() != null ? Integer.parseInt(request.getGerencia()) : nuevoTicket.getGerencia());
                nuevoTicket.setObservaciones(request.getObservaciones() != null ? request.getObservaciones() : nuevoTicket.getObservaciones());
                nuevoTicket.setRequerido(request.getRequerido() != null ? Integer.parseInt(request.getRequerido()) : nuevoTicket.getRequerido());
                nuevoTicket.setResponsable(request.getResponsable() != null ? request.getResponsable() : nuevoTicket.getResponsable());
                nuevoTicket.setSolicitante(request.getSolicitante() != null ? request.getSolicitante() : nuevoTicket.getSolicitante());
                nuevoTicket.setTema(request.getTema() != null ? Integer.parseInt(request.getTema()) : nuevoTicket.getTema());
                nuevoTicket.setTicket(request.getTicket() != null ? request.getTicket() : nuevoTicket.getTicket());
                nuevoTicket.setTipo(request.getTipo() != null ? Integer.parseInt(request.getTipo()) : nuevoTicket.getTipo());
                
                ticketActualizado = ticketRepository.save(nuevoTicket);
                 
            } else {
                return ticketActualizado;
            }
        } catch (Exception e) {
            logger.info("Error: " + e.getCause());
            logger.info("Error: " + e.getMessage());
        }
        return ticketActualizado;
    }

    public boolean eliminarTicket(Integer id){
        Optional<Ticket> ticketActual = ticketRepository.findById(id);
        
        try {
            if (ticketActual.isPresent()) { 
                Ticket ticketActualizado = ticketActual.get();               
                ticketRepository.deleteById(ticketActualizado.getId());                 
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.info("Error: " + e.getCause());
            logger.info("Error: " + e.getMessage());
        }
        return true;
    }
}
