package com.example.api_sistemafinanceiro.gui.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "centrodecusto")
public class CentroDeCusto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCentrodeCusto")
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Column(columnDefinition = "TEXT")
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @ManyToMany(mappedBy = "centrosDeCustos")
    @JsonBackReference
    private List<Titulo> titulos;
}
