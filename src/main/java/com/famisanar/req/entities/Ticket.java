package com.famisanar.req.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "ticket")
public class Ticket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "ticket")
    private String ticket;

    @Column(name = "tema")
    private Integer tema;

    @Column(name = "descrición")
    private String descrición;

    @Column(name = "solicitante")
    private String solicitante;

    @Column(name = "gerencia")
    private Integer gerencia;

    @Column(name = "fecha_sol")
    private Date fechaSol;

    @Column(name = "responsable")
    private String responsable;

    @Column(name = "estado_ti")
    private Integer estadoTI;

    @Column(name = "requerido")
    private String requerido;

    @Column(name = "de_ley")
    private String deLey;

    @Column(name = "Observaciones")
    private String Observaciones;

    public Ticket() {
    }
}
