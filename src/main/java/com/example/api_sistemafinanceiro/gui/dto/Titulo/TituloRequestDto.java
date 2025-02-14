package com.example.api_sistemafinanceiro.gui.dto.Titulo;

import com.example.api_sistemafinanceiro.gui.domain.enums.ETipoTitulo;
import com.example.api_sistemafinanceiro.gui.dto.CentroDeCusto.CentrodeCustoRequestDto;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TituloRequestDto {
    private Long id;

    private String descricao;

    private ETipoTitulo tipo;

    private List<CentrodeCustoRequestDto> centrosdeCustos;

    private Double valor;

    private Date dataCadastro;

    private Date dataReferencia;

    private Date dataVencimento;

    private Date dataPagamento;

    private String observacao;
}
