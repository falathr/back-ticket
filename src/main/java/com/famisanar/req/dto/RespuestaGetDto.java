package com.famisanar.req.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespuestaGetDto {
    private Integer id;
    private Integer tipo;
    private String ticket;
    private Integer tema;
    private String descricion;
    private String solicitante;
    private Integer gerencia;
    private LocalDate fechaSol;
    private String responsable;
    private Integer caso;
    private Integer requerido;
    private Integer deLey;
    private String observaciones;
    private String descGerencia;
    private String descTema;
    private String descCaso;
    private String descTipo;
    private String descRequerido;
    private String descDeLey;

    public RespuestaGetDto() {
    }

    
}
