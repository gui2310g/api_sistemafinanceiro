package com.example.api_sistemafinanceiro.gui.dto.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
public class UsuarioResponseDto {
    private Long id;

    private String nome;

    private String email;

    private String foto;

    private Date dataInativacao;

    private Date dataCadastro;

    private String role;
}
