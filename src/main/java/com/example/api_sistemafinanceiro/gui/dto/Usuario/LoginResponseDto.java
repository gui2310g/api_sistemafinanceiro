package com.example.api_sistemafinanceiro.gui.dto.Usuario;

import lombok.Data;

@Data
public class LoginResponseDto {
    private String token;

    private UsuarioResponseDto usuario;
}
