package com.famisanar.req.request;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketRequest implements Serializable{
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "El tipo es obligatorio")
    @Pattern(regexp = "^-?[1-2]+$", message = "El codigo tipo no es valido")
    private String tipo;

    @NotEmpty(message = "El codigo del ticket es obligatorio")
    private String ticket;

    @NotEmpty(message = "El codigo del ticket es obligatorio")
    private String numeroCaso;

    @NotEmpty(message = "El codigo del ticket es obligatorio")
    @Pattern(regexp = "^-?[0-9]+$", message = "El codigo del ticket no es valido")
    private String tema;

    @NotEmpty(message = "La descripción es obligatorio")
    private String descripcion;

    @NotEmpty(message = "El codigo del ticket es obligatorio")
    @Pattern(regexp = "^-?[0-9]+$", message = "El campo solicitante solo debe contener numeros")
    private String solicitante;

    @NotEmpty(message = "La gerencia es obligatorio")
    @Pattern(regexp = "^-?[1-4]+$", message = "El codigo gerencia no es valido")
    private String gerencia;

    @NotEmpty(message = "La fecha es obligatorio")
    private String fechaSol;

    @NotEmpty(message = "El responsable es obligatorio")
    @Pattern(regexp = "^-?[0-9]+$", message = "El campo responsable solo debe contener numeros")
    private String responsable;

    @NotEmpty(message = "El estado TI es obligatorio")
    @Pattern(regexp = "^-?[1-4]+$", message = "El codigo de estado TI no es valido")
    private String estadoTI;

    @NotEmpty(message = "El campo requerido es obligatorio")
    @Pattern(regexp = "^-?[1-2]+$", message = "El campo requerido no es valido")
    private String requerido;
    
    @NotEmpty(message = "El de Ley es obligatorio")
    @Pattern(regexp = "^-?[1-2]+$", message = "El campo de Ley no es valido")
    private String deLey;

    @NotEmpty(message = "Las observaciones son obligatorias")
    private String observaciones;
    
}
