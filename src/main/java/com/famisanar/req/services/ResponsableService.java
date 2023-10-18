package com.famisanar.req.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.famisanar.req.dto.PersonaDto;
import com.famisanar.req.entities.Persona;
import com.famisanar.req.helpers.ResponsableHelper;
import com.famisanar.req.request.GestionPersonasRequest;
import com.famisanar.req.request.GestionPersonasUpRequest;
import com.famisanar.req.response.TicketResponse;

@Service
public class ResponsableService {

    private final static Logger logger = Logger.getLogger(ResponsableService.class.getName());
    
    @Autowired
    ResponsableHelper responsableHelper;

    public TicketResponse listaResponsable(){
        TicketResponse response = new TicketResponse();
        List<PersonaDto> responsables = responsableHelper.listaResponsable();
        if (responsables.size()==0) {
            response.setCodigoRespuesta("001");
            response.setDescripcion("No hay datos");
        } else {
            response.setCodigoRespuesta("000");
            response.setDescripcion("Consulta exitosa");
            response.setDatos(responsables);
        }
        return response;
    }

    public TicketResponse personaResponsable(){
        TicketResponse response = new TicketResponse();
        List<Persona> responsables = responsableHelper.personasResponsable();
        if (responsables.size()==0) {
            response.setCodigoRespuesta("001");
            response.setDescripcion("No hay datos");
        } else {
            response.setCodigoRespuesta("000");
            response.setDescripcion("Consulta exitosa");
            response.setDatos(responsables);
        }
        return response;
    }

    public TicketResponse personaSolicitante(){
        TicketResponse response = new TicketResponse();
        List<Persona> responsables = responsableHelper.personasSolicitante();
        if (responsables.size()==0) {
            response.setCodigoRespuesta("001");
            response.setDescripcion("No hay datos");
        } else {
            response.setCodigoRespuesta("000");
            response.setDescripcion("Consulta exitosa");
            response.setDatos(responsables);
        }
        return response;
    }

    public TicketResponse agrearPersona(GestionPersonasRequest request){
        TicketResponse response = new TicketResponse();
        boolean respuesta = responsableHelper.agregarPersona(request); 
        if (respuesta) {
            response.setCodigoRespuesta("000");
            response.setDescripcion("insert exitosa");            
        } else {
            response.setCodigoRespuesta("001");
            response.setDescripcion("La persona ya existe");
        }
        return response;
    }

    public TicketResponse actualizarPersona(GestionPersonasUpRequest request, Integer id){
        TicketResponse response = responsableHelper.actualizarPersona(request, id);
        return response;
    }

     //Este método se encarga de actualizar datos a partir de una solicitud
     public TicketResponse eliminarPersona(Integer id){
        TicketResponse response = new TicketResponse();
        try {
            // Llamamos a la función de helper para realizar la actualización
            boolean persona = responsableHelper.eliminarPersona(id);
            if (!persona) {
                // Configuramos la respuesta con código de respuesta 001 y mensaje "No hay
                // datos"
                response.setCodigoRespuesta("001");
                response.setDescripcion("No hay datos");
            } else {
                // Si hay resultados, configuramos la respuesta con código de respuesta 000 y
                // mensaje "Actualización exitosa"
                response.setCodigoRespuesta("000");
                response.setDescripcion("Se elimino el registro");
                // Agregamos los datos de la lista a la respuesta
            }
        } catch (Exception e) {
            // Si hay una excepción, configuramos la respuesta con código de respuesta 999 y
            // mensaje "Fallo consulta"
            response.setCodigoRespuesta("999");
            response.setDescripcion("Fallo eliminando");

            // Registramos información sobre la excepción en el logger
            logger.info("Error: " + e.getCause());
            logger.info("Error: " + e.getMessage());
        }
        // Devolvemos la respuesta
        return response;
    }
}
