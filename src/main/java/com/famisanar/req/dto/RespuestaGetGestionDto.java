package com.famisanar.req.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespuestaGetGestionDto {
    private Integer id;
    private String descripcion;
    private Integer responsableId;
    private LocalDate fechaDescri;
    private Integer ticketId;
    private String descResponsable;
    public RespuestaGetGestionDto() {
    }

    
}
