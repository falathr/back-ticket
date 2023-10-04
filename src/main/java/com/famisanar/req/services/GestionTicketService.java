package com.famisanar.req.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.famisanar.req.entities.GestionTicket;
import com.famisanar.req.helpers.GestionTicketHelper;
import com.famisanar.req.request.GestionTicketBodyRequest;
import com.famisanar.req.response.TicketResponse;

@Service
public class GestionTicketService {
    
    @Autowired
    GestionTicketHelper gestionTicketHelper;

    public TicketResponse insertarGestion(GestionTicketBodyRequest request){
        TicketResponse response = new TicketResponse();
        boolean respuesta = gestionTicketHelper.guardarGestionTicket(request);
        if (respuesta) {
            response.setCodigoRespuesta("000");
            response.setDescripcion("Insert exitoso");
        } else {
            response.setCodigoRespuesta("001");
            response.setDescripcion("Fallo Insert");
        }
        return response;
    }
    
    public TicketResponse consultarGestion(Integer id){
        TicketResponse response = new TicketResponse();
        List<GestionTicket> gestionTickets = gestionTicketHelper.gestionTicket(id);
        if (gestionTickets.size() != 0) {
            response.setCodigoRespuesta("000");
            response.setDescripcion("Consulta exitoso");
            response.setDatos(gestionTickets);
        } else {
            response.setCodigoRespuesta("001");
            response.setDescripcion("Fallo consulta");
        }
        return response;
    }

    public TicketResponse actualizarGestion(Integer id, GestionTicketBodyRequest request){
        TicketResponse response = new TicketResponse();
        GestionTicket gestionTicket = gestionTicketHelper.actualizarGestion(id, request);
        response.setCodigoRespuesta("000");
        response.setDescripcion("Actualización exitoso");
        response.setDatos(gestionTicket);
        return response;
    }

    public TicketResponse eliminarGestion(Integer id){
        TicketResponse response = new TicketResponse();
        boolean repuesta = gestionTicketHelper.eliminarTicket(id);
        if (repuesta) {
            response.setCodigoRespuesta("000");
            response.setDescripcion("registro eliminado");
        } else {
            response.setCodigoRespuesta("001");
            response.setDescripcion("Fallo eliminado registro");
        }
        return response;
    }
}
