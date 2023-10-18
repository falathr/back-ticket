package com.famisanar.req.helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.famisanar.req.dao.ActividadRepository;
import com.famisanar.req.dao.ResponsableRepository;
import com.famisanar.req.dto.PersonaDto;
import com.famisanar.req.entities.Actividad;
import com.famisanar.req.entities.Persona;
import com.famisanar.req.request.GestionPersonasRequest;
import com.famisanar.req.request.GestionPersonasUpRequest;
import com.famisanar.req.response.TicketResponse;

@Service
public class ResponsableHelper {

    private final static Logger logger = Logger.getLogger(ResponsableHelper.class.getName());

    @Autowired
    ResponsableRepository responsableRepository;

    @Autowired
    ActividadRepository actividadRepository;

    public List<PersonaDto> listaResponsable() {
        List<Persona> responsables = responsableRepository.findAll();
        List<PersonaDto> personaDto = new ArrayList<>();
        Optional<Actividad> actividad;
        for (Persona persona : responsables) {
            PersonaDto personaDto1 = new PersonaDto();
            personaDto1.setActividad(persona.getActividad());
            personaDto1.setApellidos(persona.getApellidos());
            actividad = actividadRepository.findById(persona.getActividad());
            if (actividad.isPresent()) {
                Actividad actividadDes = actividad.get();
                personaDto1.setDesActividad(actividadDes.getDescripcion());
            }
            personaDto1.setDireccion(persona.getDireccion());
            personaDto1.setEmail(persona.getEmail());
            personaDto1.setFechaNacimiento(persona.getFechaNacimiento());
            personaDto1.setId(persona.getId());
            personaDto1.setNombres(persona.getNombres());
            personaDto1.setNumeroIdentificacion(persona.getNumeroIdentificacion());
            personaDto1.setSexo(persona.getSexo());
            personaDto1.setTelefono(persona.getTelefono());
            personaDto1.setEstado(persona.getEstado());
            if (persona.getEstado() ==1) {
                personaDto1.setDesEstado("Si");
            } else {
                personaDto1.setDesEstado("No");
            }
            personaDto.add(personaDto1);
            
        }
        return personaDto;
    }

    public List<Persona> personasResponsable() {
        List<Persona> personas = responsableRepository.personasResponsable();
        return personas;
    }

    public List<Persona> personasSolicitante() {
        List<Persona> personas = responsableRepository.personassolicitante();
        return personas;
    }

    public boolean agregarPersona(GestionPersonasRequest request) {
        boolean mensaje = false;
        Persona persona = new Persona();
        String fechaString = request.getFechaNacimiento();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Persona persona2 = responsableRepository.personaCedula(request.getNumIdentificacion());
        try {
            if (persona2 == null) {
                LocalDate date = LocalDate.parse(fechaString, formatter);
                persona.setActividad(Integer.parseInt(request.getActividad()));
                persona.setApellidos(request.getApellidos());
                persona.setDireccion(request.getDireccion());
                persona.setEmail(request.getEmail());
                persona.setFechaNacimiento(date);
                persona.setNombres(request.getNombres());
                persona.setNumeroIdentificacion(request.getNumIdentificacion());
                persona.setSexo(request.getSexo());
                persona.setTelefono(request.getTelefono());
                persona.setEstado(Integer.parseInt(request.getEstado()));
                responsableRepository.save(persona);
                mensaje = true;
            } else {
                mensaje = false;
            }

        } catch (Exception e) {
            logger.info("Error: " + e.getCause());
            logger.info("Error: " + e.getMessage());
        }
        return mensaje;
    }

    public TicketResponse actualizarPersona(GestionPersonasUpRequest request, Integer id){
        Optional<Persona> persona = responsableRepository.findById(id);
        TicketResponse response = new TicketResponse();
        String fechaString = request.getFechaNacimiento(); // Asumiendo que ticket.getFechaSol() devuelve una String en el formato "dd-MM-yyyy"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            if (persona.isPresent()) {
            Persona nuevaPersona = persona.get();
            nuevaPersona.setActividad(request.getActividad() != null ? Integer.parseInt(request.getActividad()): nuevaPersona.getActividad());
            nuevaPersona.setApellidos(request.getApellidos() != null ? request.getApellidos(): nuevaPersona.getApellidos());
            nuevaPersona.setDireccion(request.getDireccion() != null ? request.getDireccion(): nuevaPersona.getDireccion());
            nuevaPersona.setEmail(request.getEmail() != null ? request.getEmail(): nuevaPersona.getEmail());
            if (request.getFechaNacimiento() != null) {
                LocalDate date = LocalDate.parse(fechaString, formatter);
                nuevaPersona.setFechaNacimiento(date);
            } else {
                nuevaPersona.setFechaNacimiento(nuevaPersona.getFechaNacimiento());
            }
            nuevaPersona.setNombres(request.getNombres() != null ? request.getNombres(): nuevaPersona.getNombres());
            nuevaPersona.setNumeroIdentificacion(request.getNumIdentificacion() != null ? request.getNumIdentificacion(): nuevaPersona.getNumeroIdentificacion());
            nuevaPersona.setSexo(request.getSexo() != null ? request.getSexo(): nuevaPersona.getSexo());
            nuevaPersona.setTelefono(request.getTelefono() != null ? request.getTelefono(): nuevaPersona.getTelefono());
            nuevaPersona.setEstado(request.getEstado() != null ? Integer.parseInt(request.getEstado()):nuevaPersona.getEstado());

            responsableRepository.save(nuevaPersona);
            response.setCodigoRespuesta("000");
            response.setDescripcion("Actualización exitosa");
            response.setDatos(nuevaPersona);
        } else {
            response.setCodigoRespuesta("001");
            response.setDescripcion("Persona no existe");
        } 
        } catch (Exception e) {
            logger.info("Error: " + e.getCause());
            logger.info("Error: " + e.getMessage());
            response.setCodigoRespuesta("999");
            response.setDescripcion("Error fallo actualización");
        }
        
        return response;
    }

    public boolean eliminarPersona(Integer id) {
        Optional<Persona> personaActual = responsableRepository.findById(id);

        try {
            if (personaActual.isPresent()) {
                Persona ticketActualizado = personaActual.get();
                ticketActualizado.setEstado(2);
                responsableRepository.save(ticketActualizado);
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
