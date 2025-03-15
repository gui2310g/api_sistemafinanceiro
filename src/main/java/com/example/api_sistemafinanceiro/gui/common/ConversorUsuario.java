package com.example.api_sistemafinanceiro.gui.common;

import com.example.api_sistemafinanceiro.gui.domain.model.Usuario;
import com.example.api_sistemafinanceiro.gui.dto.Usuario.UsuarioDetailResponseDto;
import com.example.api_sistemafinanceiro.gui.dto.Usuario.UsuarioRequestDto;
import com.example.api_sistemafinanceiro.gui.dto.Usuario.UsuarioResponseDto;
import org.modelmapper.ModelMapper;

import java.util.Date;

public class ConversorUsuario {

    private static final ModelMapper mapper = new ModelMapper();

    public static UsuarioResponseDto converterParaDto(Usuario usuario) {
        return mapper.map(usuario, UsuarioResponseDto.class);
    }

    public static UsuarioDetailResponseDto converterParaDtoCadastrado(Usuario usuario) {
        return mapper.map(usuario, UsuarioDetailResponseDto.class);
    }

    public static Usuario converterParaModelo(UsuarioRequestDto dto) {
        return mapper.map(dto, Usuario.class);
    }
}
