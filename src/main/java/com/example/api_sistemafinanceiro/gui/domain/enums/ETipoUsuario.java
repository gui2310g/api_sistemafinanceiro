package com.example.api_sistemafinanceiro.gui.domain.enums;

import lombok.Getter;

@Getter
public enum ETipoUsuario {
    ADMINISTRADOR("ADMINISTRADOR"),
    USUARIO("USUARIO");

    private final String role;

    ETipoUsuario(String role) { this.role = role; }
}
