package com.famisanar.req.entities;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "gestion_ticket")
public class GestionTicket implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "des")
    private String descripcion;

    @Column(name = "responsable_id")
    private Integer responsableId;

    @Column(name = "fecha_desc")
    private LocalDate fechaDescri;

    @Column(name = "ticket_id")
    private Integer ticketId;

    public GestionTicket() {
    }
}
