package com.example.api_sistemafinanceiro.gui.dto.Titulo;

import com.example.api_sistemafinanceiro.gui.domain.enums.ETipoTitulo;
import com.example.api_sistemafinanceiro.gui.dto.CentroDeCusto.CentrodeCustoResponseDto;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TituloResponseDto {
    private Long id;

    private String descricao;

    private ETipoTitulo tipo;

    private List<CentrodeCustoResponseDto> centrosdeCustos;

    private Double valor;

    private Date dataCadastro;

    private Date dataReferencia;

    private Date dataVencimento;

    private Date dataPagamento;

    private String observacao;
}
