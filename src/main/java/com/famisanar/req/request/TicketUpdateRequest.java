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

    @Pattern(regexp = "^-?[0-9]+$", message = "El codigo del tema no es valido")
    private String tema;

    private String descripcion;

    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]*$", message = "El campo solo debe contener letras")
    private String solicitante;

    @Pattern(regexp = "^-?[1-2]+$", message = "El codigo gerencia no es valido")
    private String gerencia;

    private String fechaSol;

    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]*$", message = "El campo solo debe contener letras")
    private String responsable;

    @Pattern(regexp = "^-?[1-2]+$", message = "El codigo de estado TI no es valido")
    private String estadoTI;

    @Pattern(regexp = "^-?[1-2]+$", message = "El campo requerido no es valido")
    private String requerido;

    @Pattern(regexp = "^-?[1-2]+$", message = "El campo de Ley no es valido")
    private String deLey;

    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]*$", message = "El campo solo debe contener letras")
    private String observaciones;

    public TicketUpdateRequest() {
    }

}
