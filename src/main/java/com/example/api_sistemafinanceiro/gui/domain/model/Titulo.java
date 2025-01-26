package com.example.api_sistemafinanceiro.gui.domain.model;

import com.example.api_sistemafinanceiro.gui.domain.enums.ETipoTitulo;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class Titulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTitulo")
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    private ETipoTitulo tipo;

    @ManyToOne
    @JoinColumn(name = "idCentroDeCusto")
    private CentroDeCusto centroDeCusto;

    @ManyToMany
    @JoinTable(
            name = "titulo_centrodecusto",
            joinColumns = @JoinColumn(name = "idTitulo"),
            inverseJoinColumns = @JoinColumn(name = "idCentroDeCusto")
    )
    private List<CentroDeCusto> centrosDeCustos;

    @Column(nullable = false)
    private Double valor;

    private Date dataCadastro;

    private Date dataReferencia;

    private Date dataVencimento;

    private Date dataPagamento;

    @Column(columnDefinition = "TEXT")
    private String observacao;

}
