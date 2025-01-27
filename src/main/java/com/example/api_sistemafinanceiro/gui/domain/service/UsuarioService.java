package com.example.api_sistemafinanceiro.gui.domain.service;

import com.example.api_sistemafinanceiro.gui.dto.Usuario.UsuarioRequestDto;
import com.example.api_sistemafinanceiro.gui.dto.Usuario.UsuarioResponseDto;

import java.util.List;

public class UsuarioService implements ICrudService<UsuarioRequestDto, UsuarioResponseDto> {

    @Override
    public List<UsuarioResponseDto> findAll() {
        return List.of();
    }

    @Override
    public UsuarioResponseDto findById(Long id) {
        return null;
    }

    @Override
    public UsuarioResponseDto create(UsuarioRequestDto dto) {
        return null;
    }

    @Override
    public UsuarioResponseDto update(Long id, UsuarioRequestDto dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
