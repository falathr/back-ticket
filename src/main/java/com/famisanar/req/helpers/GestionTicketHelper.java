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
import com.famisanar.req.dao.ResponsableRepository;
import com.famisanar.req.dto.RespuestaGetGestionDto;
import com.famisanar.req.entities.GestionTicket;
import com.famisanar.req.entities.Persona;
import com.famisanar.req.request.GestionTicketBodyRequest;

@Service
public class GestionTicketHelper {

    private final static Logger logger = Logger.getLogger(TicketHelper.class.getName());

    @Autowired
    GestionTicketRepository gestionTicketRepository;

    @Autowired
    ResponsableRepository responsableRepository;

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
    public List<RespuestaGetGestionDto> gestionTicket(Integer id) {

        List<RespuestaGetGestionDto> gestionTickets = new ArrayList<>();
        List<GestionTicket> gestionTicket = new ArrayList<>();
        gestionTicket = gestionTicketRepository.findByTicketId(id);
        for (GestionTicket gestionTicket2 : gestionTicket) {
            RespuestaGetGestionDto dto = new RespuestaGetGestionDto();
            Optional<Persona> responsable = responsableRepository.findById(gestionTicket2.getResponsableId());
            if (responsable.isPresent()) {
                Persona responsable4 = responsable.get();
                dto.setDescResponsable(responsable4.getNombres() +" " + responsable4.getApellidos());
            }
            dto.setDescripcion(gestionTicket2.getDescripcion());
            dto.setFechaDescri(gestionTicket2.getFechaDescri());
            dto.setId(gestionTicket2.getId());
            dto.setResponsableId(gestionTicket2.getResponsableId());
            dto.setTicketId(gestionTicket2.getTicketId());
            gestionTickets.add(dto);
        }
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
