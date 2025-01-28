package com.example.api_sistemafinanceiro.gui.mappers;

import com.example.api_sistemafinanceiro.gui.domain.model.Usuario;
import com.example.api_sistemafinanceiro.gui.dto.Usuario.UsuarioResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioResponseDto toUsuarioResponseDto(Usuario usuario);
}
