package com.example.api_sistemafinanceiro.gui.dto.Usuario;

import lombok.Data;

@Data
public class UsuarioRequestDto {

    private String nome;

    private String email;

    private String senha;

    private String foto;
}
