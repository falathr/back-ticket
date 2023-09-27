package com.famisanar.req.entities;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "gerencia")
public class Gerencia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "des")
    private String descripcion;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "gerencia_id", referencedColumnName = "id")
    private List<Ticket> tickets;

    public Gerencia() {
    }

}
