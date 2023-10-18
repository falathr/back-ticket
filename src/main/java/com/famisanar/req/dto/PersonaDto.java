package com.famisanar.req.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PersonaDto {
    private Integer id;
    private String numeroIdentificacion;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String telefono;
    private String email;
    private LocalDate fechaNacimiento;
    private String sexo;
    private Integer actividad;
    private String desActividad;
    private Integer estado;
    private String desEstado;
    public PersonaDto() {
    }
    
}
