package com.famisanar.req.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

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
    private Integer tipo;

    @Column(name = "ticket")
    private String ticket;

    @Column(name = "tema_id")
    private Integer tema;

    @Column(name = "descricion")
    private String descricion;

    @Column(name = "gerencia_id")
    private Integer gerencia;

    @Column(name = "fecha_sol")
    private LocalDate fechaSol;

    @Column(name = "responsable_id")
    private Integer responsable;

    @Column(name = "caso_id")
    private Integer caso;

    @Column(name = "requerido")
    private Integer requerido;

    @Column(name = "de_ley")
    private Integer deLey;

    @Column(name = "Observaciones")
    private String observaciones;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id", referencedColumnName = "id")
    private List<GestionTicket> gestionTickets;

    @Column(name = "solicitante_id")
    private Integer solicitante;

    @Column(name = "NUMERO_CASO")
    private String numeroCaso;

    public Ticket() {
    }
}
