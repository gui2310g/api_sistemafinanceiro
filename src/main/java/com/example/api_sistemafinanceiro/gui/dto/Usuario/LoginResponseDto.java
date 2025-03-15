package com.example.api_sistemafinanceiro.gui.dto.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDto {
    private String token;

    private String role;
}
