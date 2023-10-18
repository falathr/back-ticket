package com.famisanar.req.request;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketUpdateRequest {

    @Pattern(regexp = "^-?[1-2]+$", message = "El codigo tipo no es valido")
    private String tipo;

    private String ticket;

    private String numeroCaso;

    @Pattern(regexp = "^-?[0-9]+$", message = "El codigo del tema no es valido")
    private String tema;

    private String descripcion;

    @Pattern(regexp = "^-?[0-9]+$", message = "El campo solo debe contener numeros")
    private String solicitante;

    @Pattern(regexp = "^-?[1-4]+$", message = "El codigo gerencia no es valido")
    private String gerencia;

    private String fechaSol;

    @Pattern(regexp = "^-?[0-9]+$", message = "El campo solo debe contener numeros")
    private String responsable;

    @Pattern(regexp = "^-?[1-3]+$", message = "El codigo de estado TI no es valido")
    private String estadoTI;

    @Pattern(regexp = "^-?[1-2]+$", message = "El campo requerido no es valido")
    private String requerido;

    @Pattern(regexp = "^-?[1-2]+$", message = "El campo de Ley no es valido")
    private String deLey;

    private String observaciones;

    public TicketUpdateRequest() {
    }

}
