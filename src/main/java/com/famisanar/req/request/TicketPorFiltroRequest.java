package com.famisanar.req.request;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketPorFiltroRequest {
    
    @Pattern(regexp = "^-?[1-2]+$", message = "El campo de Ley no es valido")
    private String id;

    private String valor;

    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+$", message = "El campo solo debe contener letras")
    private String nombreFiltro;
    
    public TicketPorFiltroRequest() {
    }
    
}
