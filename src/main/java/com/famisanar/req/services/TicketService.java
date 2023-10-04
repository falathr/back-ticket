package com.famisanar.req.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.famisanar.req.dto.RespuestaGetDto;
import com.famisanar.req.entities.Ticket;
import com.famisanar.req.helpers.TicketHelper;
import com.famisanar.req.request.TicketRequest;
import com.famisanar.req.request.TicketUpdateRequest;
import com.famisanar.req.request.TicketDatosFiltros;
import com.famisanar.req.response.TicketResponse;

@Service
public class TicketService {

    @Autowired
    TicketHelper ticketHelper;

    private final static Logger logger = Logger.getLogger(TicketHelper.class.getName());

    // Este método se encarga de insertar datos a partir de una solicitud
    public TicketResponse insertarDatos(TicketRequest request) {
        TicketResponse ticketResponse = new TicketResponse();

        // Llamada al método guardarTicket para intentar guardar los datos
        boolean activar = ticketHelper.guardarTicket(request);

        // Verificamos si el guardado fue exitoso o no
        if (activar) {
            // Si fue exitoso, configuramos la respuesta con código 000 y mensaje de éxito
            ticketResponse.setCodigoRespuesta("000");
            ticketResponse.setDescripcion("Insert exitoso");
        } else {
            // Si no fue exitoso, configuramos la respuesta con código 999 y mensaje de
            // fallo
            ticketResponse.setCodigoRespuesta("999");
            ticketResponse.setDescripcion("Fallo Insert");
        }

        // Devolvemos la respuesta
        return ticketResponse;
    }

    // Este método se encarga de buscar datos a partir de una solicitud
    public TicketResponse buscarTickets(TicketDatosFiltros request, Integer paginador, Integer cantidad) {
        TicketResponse ticketResponse = new TicketResponse();

        // Llamamos a la función de helper para realizar la consulta con filtros
        List<RespuestaGetDto> list = ticketHelper.consultaConFiltros(request, paginador, cantidad );

        try {
            // Si la lista de resultados está vacía
            if (list.size() == 0) {
                // Configuramos la respuesta con código de respuesta 001 y mensaje "No hay
                // datos"
                ticketResponse.setCodigoRespuesta("001");
                ticketResponse.setDescripcion("No hay datos");
            } else {
                // Si hay resultados, configuramos la respuesta con código de respuesta 000 y
                // mensaje "Consulta Exitosa"
                ticketResponse.setCodigoRespuesta("000");
                ticketResponse.setDescripcion("Consulta Exitosa");

                // Agregamos los datos de la lista a la respuesta
                ticketResponse.setDatos(list);
            }
        } catch (Exception e) {
            // Si hay una excepción, configuramos la respuesta con código de respuesta 999 y
            // mensaje "Fallo consulta"
            ticketResponse.setCodigoRespuesta("999");
            ticketResponse.setDescripcion("Fallo consulta");

            // Registramos información sobre la excepción en el logger
            logger.info("Error: " + e.getCause());
            logger.info("Error: " + e.getMessage());
        }

        // Devolvemos la respuesta
        return ticketResponse;
    }

    //Este método se encarga de actualizar datos a partir de una solicitud
    public TicketResponse actualizarTicket(Integer id, TicketUpdateRequest request){
        TicketResponse response = new TicketResponse();
        try {
            // Llamamos a la función de helper para realizar la actualización
            Ticket ticket = ticketHelper.actualizarTicket(id, request);
            if (ticket.getId() == null) {
                // Configuramos la respuesta con código de respuesta 001 y mensaje "No hay
                // datos"
                response.setCodigoRespuesta("001");
                response.setDescripcion("No hay datos");
            } else {
                // Si hay resultados, configuramos la respuesta con código de respuesta 000 y
                // mensaje "Actualización exitosa"
                response.setCodigoRespuesta("000");
                response.setDescripcion("Actualización exitosa");
                // Agregamos los datos de la lista a la respuesta
                response.setDatos(ticket);
            }
        } catch (Exception e) {
            // Si hay una excepción, configuramos la respuesta con código de respuesta 999 y
            // mensaje "Fallo consulta"
            response.setCodigoRespuesta("999");
            response.setDescripcion("Fallo actualización");

            // Registramos información sobre la excepción en el logger
            logger.info("Error: " + e.getCause());
            logger.info("Error: " + e.getMessage());
        }
        // Devolvemos la respuesta
        return response;
    }

    //Este método se encarga de actualizar datos a partir de una solicitud
    public TicketResponse eliminarTicket(Integer id){
        TicketResponse response = new TicketResponse();
        try {
            // Llamamos a la función de helper para realizar la actualización
            boolean ticket = ticketHelper.eliminarTicket(id);
            if (!ticket) {
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
                response.setDatos(ticket);
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
