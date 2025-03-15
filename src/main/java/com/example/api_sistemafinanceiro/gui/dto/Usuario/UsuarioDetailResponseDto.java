package com.example.api_sistemafinanceiro.gui.dto.Usuario;

import lombok.Data;

import java.util.Date;

@Data
public class UsuarioDetailResponseDto {
    private Long id;

    private String nome;

    private String email;

    private String foto;

    private Date dataCadastro;

    private String role;

    private String token;
}
