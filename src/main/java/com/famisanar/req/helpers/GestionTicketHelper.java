package com.famisanar.req.helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.famisanar.req.dao.GestionTicketRepository;
import com.famisanar.req.entities.GestionTicket;
import com.famisanar.req.request.GestionTicketBodyRequest;

@Service
public class GestionTicketHelper {

    private final static Logger logger = Logger.getLogger(TicketHelper.class.getName());

    @Autowired
    GestionTicketRepository gestionTicketRepository;

    //Metodo post
    public boolean guardarGestionTicket(GestionTicketBodyRequest request) {
        GestionTicket gestionTicket = new GestionTicket();
        try {
            // Definir el formato de la fecha
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            // Parsear el string a LocalDate
            LocalDate fechaLocal = LocalDate.parse(request.getFechaDesc(), formatter);
            gestionTicket.setFechaDescri(fechaLocal);
            gestionTicket.setDescripcion(request.getDesc());
            gestionTicket.setResponsableId(Integer.parseInt(request.getResponsable()));
            gestionTicket.setTicketId(Integer.parseInt(request.getTicket()));

            gestionTicketRepository.save(gestionTicket);
            return true;
        } catch (Exception e) {
            logger.info("Error: " + e.getCause());
            logger.info("Error: " + e.getMessage());
            return false;
        }

    }

    //Metodo get
    public List<GestionTicket> gestionTicket(Integer id) {
        List<GestionTicket> gestionTickets = new ArrayList<>();
        gestionTickets = gestionTicketRepository.findByTicketId(id);
        return gestionTickets;
    }

    //Metodo put
    public GestionTicket actualizarGestion(Integer id, GestionTicketBodyRequest request) {
        Optional<GestionTicket> optional = gestionTicketRepository.findById(id);
        GestionTicket gestionTicket = new GestionTicket();

        try {
            if (optional.isPresent()) {
                GestionTicket gestionTicket2 = optional.get();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                // Parsear el string a LocalDate
                LocalDate fechaLocal = LocalDate.parse(request.getFechaDesc(), formatter);

                gestionTicket2.setFechaDescri(fechaLocal != null ? fechaLocal : gestionTicket2.getFechaDescri());
                gestionTicket2.setDescripcion(request.getDesc() != null ? request.getDesc() : gestionTicket2.getDescripcion());
                gestionTicket2.setResponsableId(request.getResponsable() != null ? Integer.parseInt(request.getResponsable()) : gestionTicket2.getResponsableId());
                gestionTicket2.setTicketId(request.getTicket() != null ? Integer.parseInt(request.getTicket()) : gestionTicket2.getTicketId());

                gestionTicket = gestionTicketRepository.save(gestionTicket2);
                
            }
        } catch (Exception e) {
            logger.info("Error: " + e.getCause());
            logger.info("Error: " + e.getMessage());
        }
        return gestionTicket;
    }

    //Metodo Delete
    public boolean eliminarTicket(Integer id) {
        Optional<GestionTicket> ticketActual = gestionTicketRepository.findById(id);

        try {
            if (ticketActual.isPresent()) {
                GestionTicket ticketActualizado = ticketActual.get();
                gestionTicketRepository.deleteById(ticketActualizado.getId());
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
