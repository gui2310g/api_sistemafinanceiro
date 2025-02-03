package com.example.api_sistemafinanceiro.gui.dto.Usuario;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;

    private String senha;
}
