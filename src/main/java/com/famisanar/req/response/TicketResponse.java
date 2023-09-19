package com.famisanar.req.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketResponse {
    
    private String codigoRespuesta;
    private String descripcion;
    private Object datos;
    
    public TicketResponse() {
    }    
}
