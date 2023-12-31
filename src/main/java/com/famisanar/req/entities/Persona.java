package com.famisanar.req.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "persona")
public class Persona implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "identificacion")
    private String numeroIdentificacion;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "email")
    private String email;

    @Column(name = "fecha_nac")
    private LocalDate fechaNacimiento;

    @Column(name = "sexo")
    private String sexo;
    
    @Column(name = "actividad_id")
    private Integer actividad;

    @Column(name = "estado")
    private Integer estado;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsable_id", referencedColumnName = "id")
    private List<GestionTicket> gestionTickets;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "solicitante_id", referencedColumnName = "id")
    private List<Ticket> solicitanteTicket;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsable_id", referencedColumnName = "id")
    private List<Ticket> responsableTicket;

    public Persona() {
    }
}
