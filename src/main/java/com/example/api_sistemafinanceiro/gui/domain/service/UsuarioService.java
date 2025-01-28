package com.example.api_sistemafinanceiro.gui.domain.service;

import com.example.api_sistemafinanceiro.gui.domain.exception.ResourceNotFoundException;
import com.example.api_sistemafinanceiro.gui.domain.model.Usuario;
import com.example.api_sistemafinanceiro.gui.domain.repository.UsuarioRepository;
import com.example.api_sistemafinanceiro.gui.dto.Usuario.UsuarioRequestDto;
import com.example.api_sistemafinanceiro.gui.dto.Usuario.UsuarioResponseDto;
import com.example.api_sistemafinanceiro.gui.mappers.UsuarioMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService implements ICrudService<UsuarioRequestDto, UsuarioResponseDto> {

    private UsuarioRepository usuarioRepository;

    private UsuarioMapper usuarioMapper;

    @Override
    public List<UsuarioResponseDto> findAll() throws ResourceNotFoundException {
        List<Usuario> usuarios = usuarioRepository.findAll();

        if(usuarios.isEmpty()) throw new ResourceNotFoundException("Não tem usuarios cadastrados");

        return usuarios.stream().map(usuarioMapper::toUsuarioResponseDto).toList();
    }

    @Override
    public UsuarioResponseDto findById(Long id) throws ResourceNotFoundException {
        return usuarioRepository.findById(id).map(usuarioMapper::toUsuarioResponseDto)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find user with this id"));
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
