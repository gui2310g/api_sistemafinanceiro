package com.example.api_sistemafinanceiro.gui.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public class ErrorResposta {
    private String dataHora;

    private Integer status;

    private String titulo;

    private String mensagem;
}
