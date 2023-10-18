package com.famisanar.req.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.famisanar.req.request.GestionPersonasRequest;
import com.famisanar.req.request.GestionPersonasUpRequest;
import com.famisanar.req.request.GestionTicketBodyRequest;
import com.famisanar.req.request.TicketDatosFiltros;
import com.famisanar.req.request.TicketRequest;
import com.famisanar.req.request.TicketUpdateRequest;
import com.famisanar.req.response.TicketResponse;
import com.famisanar.req.services.ActividadService;
import com.famisanar.req.services.CasosService;
import com.famisanar.req.services.GerenciaService;
import com.famisanar.req.services.GestionTicketService;
import com.famisanar.req.services.ResponsableService;
import com.famisanar.req.services.TemaService;
import com.famisanar.req.services.TicketService;

import jakarta.validation.Valid;

@CrossOrigin(origins = { "*" }, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
@RestController
@RequestMapping("/ServiciosRest")
public class servicioRest {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private CasosService casosService;

    @Autowired
    private GerenciaService gerenciaService;

    @Autowired 
    private TemaService temaService;

    @Autowired
    private GestionTicketService gestionTicketService;

    @Autowired
    private ResponsableService responsableService;

    @Autowired
    private ActividadService actividadService;

    @RequestMapping(path = "/ticket", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public TicketResponse insertarTicket(@Valid @RequestBody TicketRequest request, BindingResult result) {
        TicketResponse response = new TicketResponse();

        // Verificar si hay errores de validación en la solicitud
        if (result.hasErrors()) {
            // Si hay errores, configurar la respuesta con el mensaje de error
            response.setDescripcion(result.getFieldError().getDefaultMessage());
            return response; // Devolver la respuesta con el mensaje de error
        }

        // Si no hay errores de validación, llamar al servicio para insertar el ticket
        response = ticketService.insertarDatos(request);

        return response; // Devolver la respuesta con el resultado de la inserción
    }

    @RequestMapping(path = "/tickets", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public TicketResponse buscarTicket(@Valid @RequestBody TicketDatosFiltros request, BindingResult result, @RequestHeader(value = "pageNumber", required = false) Integer pageNumber) {
        TicketResponse response = new TicketResponse();

        // Verificar si hay errores de validación en la solicitud
        if (result.hasErrors()) {
            // Si hay errores, configurar la respuesta con el mensaje de error
            response.setDescripcion(result.getFieldError().getDefaultMessage());
            return response; // Devolver la respuesta con el mensaje de error
        }

        if (pageNumber == null) {
            pageNumber=0;
        }
        // Si no hay errores de validación, llamar al servicio para buscar tickets
        response = ticketService.buscarTickets(request, pageNumber, 1000);

        return response; // Devolver la respuesta con los resultados de la búsqueda
    }

    @RequestMapping(path = "/ticket/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public TicketResponse actualizarTicket(@Valid @RequestBody TicketUpdateRequest request, @PathVariable Integer id,  BindingResult result) {
        TicketResponse response = new TicketResponse();

        // Verificar si hay errores de validación en la solicitud
        if (result.hasErrors()) {
            // Si hay errores, configurar la respuesta con el mensaje de error
            response.setDescripcion(result.getFieldError().getDefaultMessage());
            return response; // Devolver la respuesta con el mensaje de error
        }

        // Si no hay errores de validación, llamar al servicio para Actualizar tickets
        response = ticketService.actualizarTicket(id, request);

        return response; // Devolver la respuesta con los resultados de la búsqueda
    }

    @RequestMapping(path = "/ticket/{id}", method = RequestMethod.DELETE)
    public TicketResponse eliminarTicket(@PathVariable Integer id) {
        TicketResponse response = new TicketResponse();

        // Si no hay errores de validación, llamar al servicio para Actualizar tickets
        response = ticketService.eliminarTicket(id);

        return response; // Devolver la respuesta con los resultados de la búsqueda
    }

    @RequestMapping(path = "/casos", method = RequestMethod.GET)
    public TicketResponse consultarCasos() {
        TicketResponse response = new TicketResponse();

        // Si no hay errores de validación, llamar al servicio para insertar el ticket
        response = casosService.casosLista();

        return response; // Devolver la respuesta con el resultado de la inserción
    }

    @RequestMapping(path = "/gerencias", method = RequestMethod.GET)
    public TicketResponse consultarGerencia() {
        TicketResponse response = new TicketResponse();

        // Si no hay errores de validación, llamar al servicio para insertar el ticket
        response = gerenciaService.consultarGerencia();

        return response; // Devolver la respuesta con el resultado de la inserción
    }

    @RequestMapping(path = "/temas", method = RequestMethod.GET)
    public TicketResponse consultartemas() {
        TicketResponse response = new TicketResponse();

        // Si no hay errores de validación, llamar al servicio para insertar el ticket
        response = temaService.consultarTemas();

        return response; // Devolver la respuesta con el resultado de la inserción
    }

    @RequestMapping(path = "/gestion/{id}", method = RequestMethod.GET)
    public TicketResponse consultarGestionTicket(@PathVariable Integer id) {
        TicketResponse response = new TicketResponse();

        // Si no hay errores de validación, llamar al servicio para consultar la gestión
        response = gestionTicketService.consultarGestion(id);

        return response; // Devolver la respuesta con el resultado de la consulta
    }

    @RequestMapping(path = "/gestion", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public TicketResponse agregarGestion(@Valid @RequestBody GestionTicketBodyRequest request, BindingResult result) {
        TicketResponse response = new TicketResponse();

        // Verificar si hay errores de validación en la solicitud
        if (result.hasErrors()) {
            // Si hay errores, configurar la respuesta con el mensaje de error
            response.setDescripcion(result.getFieldError().getDefaultMessage());
            return response; // Devolver la respuesta con el mensaje de error
        }

        // Si no hay errores de validación, llamar al servicio para insertar 
        response = gestionTicketService.insertarGestion(request);

        return response; // Devolver la respuesta con el resultado
    }

    @RequestMapping(path = "/gestion/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public TicketResponse actualizarGestion(@Valid @RequestBody GestionTicketBodyRequest request, @PathVariable Integer id,  BindingResult result) {
        TicketResponse response = new TicketResponse();

        // Verificar si hay errores de validación en la solicitud
        if (result.hasErrors()) {
            // Si hay errores, configurar la respuesta con el mensaje de error
            response.setDescripcion(result.getFieldError().getDefaultMessage());
            return response; // Devolver la respuesta con el mensaje de error
        }

        // Si no hay errores de validación, llamar al servicio para Actualizar tickets
        response = gestionTicketService.actualizarGestion(id, request);

        return response; // Devolver la respuesta con los resultados de la búsqueda
    }

    @RequestMapping(path = "/gestion/{id}", method = RequestMethod.DELETE)
    public TicketResponse eliminarGestion(@PathVariable Integer id) {
        TicketResponse response = new TicketResponse();

        // Si no hay errores de validación, llamar al servicio para Actualizar tickets
        response = gestionTicketService.eliminarGestion(id);

        return response; // Devolver la respuesta con los resultados de la búsqueda
    }

    @RequestMapping(path = "/persona", method = RequestMethod.GET)
    public TicketResponse consultarResponsable() {
        TicketResponse response = new TicketResponse();

        // lista las personas 
        response = responsableService.listaResponsable();

        return response; // Devolver la respuesta con la lista de personas
    }

    @RequestMapping(path = "/personaResponsable", method = RequestMethod.GET)
    public TicketResponse consultarPersonaResponsable() {
        TicketResponse response = new TicketResponse();

        // lista las personas responsables
        response = responsableService.personaResponsable();

        return response; // Devolver la respuesta con la lista de personas
    }

    @RequestMapping(path = "/personaSolicitante", method = RequestMethod.GET)
    public TicketResponse consultarPersonaSolicitante() {
        TicketResponse response = new TicketResponse();

        // lista las personas solicitantes
        response = responsableService.personaSolicitante();

        return response; // Devolver la respuesta con la lista de personas
    }

    @RequestMapping(path = "/persona", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public TicketResponse agregarPersona(@Valid @RequestBody GestionPersonasRequest request, BindingResult result) {
        TicketResponse response = new TicketResponse();

        // Verificar si hay errores de validación en la solicitud
        if (result.hasErrors()) {
            // Si hay errores, configurar la respuesta con el mensaje de error
            response.setDescripcion(result.getFieldError().getDefaultMessage());
            return response; // Devolver la respuesta con el mensaje de error
        }

        // Si no hay errores de validación, llamar al servicio para insertar 
        response = responsableService.agrearPersona(request);

        return response; // Devolver la respuesta con el resultado
    }


    @RequestMapping(path = "/persona/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public TicketResponse actualizarPersona(@Valid @RequestBody GestionPersonasUpRequest request, @PathVariable Integer id,  BindingResult result) {
        TicketResponse response = new TicketResponse();

        // Verificar si hay errores de validación en la solicitud
        if (result.hasErrors()) {
            // Si hay errores, configurar la respuesta con el mensaje de error
            response.setDescripcion(result.getFieldError().getDefaultMessage());
            return response; // Devolver la respuesta con el mensaje de error
        }

        // Si no hay errores de validación, llamar al servicio para Actualizar tickets
        response = responsableService.actualizarPersona(request, id);

        return response; // Devolver la respuesta con los resultados de la búsqueda
    }

    @RequestMapping(path = "/persona/{id}", method = RequestMethod.DELETE)
    public TicketResponse eliminarPersona(@PathVariable Integer id) {
        TicketResponse response = new TicketResponse();

        // Si no hay errores de validación, llamar al servicio para Actualizar tickets
        response = responsableService.eliminarPersona(id);

        return response; // Devolver la respuesta con los resultados de la búsqueda
    }

    @RequestMapping(path = "/actividad", method = RequestMethod.GET)
    public TicketResponse consultarActividades() {
        TicketResponse response = new TicketResponse();

        // Si no hay errores de validación, llamar al servicio para insertar el ticket
        response = actividadService.actividadLista();

        return response; // Devolver la respuesta con el resultado de la inserción
    }
}
