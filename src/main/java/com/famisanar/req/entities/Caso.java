package com.famisanar.req.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "caso")
public class Caso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "des")
    private String descripcion;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "caso_id", referencedColumnName = "id")
    private List<Ticket> tickets;

    public Caso() {
    }

    

}

