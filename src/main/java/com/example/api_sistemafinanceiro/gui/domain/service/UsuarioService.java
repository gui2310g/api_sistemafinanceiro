package com.example.api_sistemafinanceiro.gui.domain.service;

import com.example.api_sistemafinanceiro.gui.common.ConversorUsuario;
import com.example.api_sistemafinanceiro.gui.domain.exception.ResourceBadRequestException;
import com.example.api_sistemafinanceiro.gui.domain.exception.ResourceNotFoundException;
import com.example.api_sistemafinanceiro.gui.domain.model.Usuario;
import com.example.api_sistemafinanceiro.gui.domain.repository.UsuarioRepository;
import com.example.api_sistemafinanceiro.gui.dto.Usuario.UsuarioDetailResponseDto;
import com.example.api_sistemafinanceiro.gui.dto.Usuario.UsuarioRequestDto;
import com.example.api_sistemafinanceiro.gui.dto.Usuario.UsuarioResponseDto;
import com.example.api_sistemafinanceiro.gui.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioService implements ICrudService<UsuarioRequestDto, UsuarioResponseDto> {

    private UsuarioRepository usuarioRepository;

    private PasswordEncoder passwordEncoder;

    private JwtUtil jwtUtil;

    @Override
    public List<UsuarioResponseDto> findAll() {
        return usuarioRepository.findAll().stream().map(ConversorUsuario::converterParaDto).toList();
    }

    @Override
    public UsuarioResponseDto findById(Long id) {
        return usuarioRepository.findById(id).map(ConversorUsuario::converterParaDto)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi achado usuario com esse id" + id));
    }

    public UsuarioResponseDto findByEmail(String email) {
        return usuarioRepository.findByEmail(email).map(ConversorUsuario::converterParaDto)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi achado usuario com esse email"));
    }

    public UsuarioDetailResponseDto create(UsuarioRequestDto dto) {
       validarUsuario(dto);

       if(usuarioRepository.findByEmail(dto.getEmail()).isPresent())
           throw new ResourceBadRequestException("Email ja existente");

       Usuario usuario = ConversorUsuario.converterParaModelo(dto);
       usuario.setDataCadastro(new Date());
       usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
       String token = jwtUtil.generateToken(usuario.getEmail(), usuario.getRole().name());
       UsuarioDetailResponseDto usuarioDto = ConversorUsuario.converterParaDtoCadastrado(usuarioRepository.save(usuario));
       usuarioDto.setToken(token);

       return usuarioDto;
    }

    @Override
    public UsuarioResponseDto update(Long id, UsuarioRequestDto dto) {
        UsuarioResponseDto usuarioBanco = findById(id);
        validarUsuario(dto);

        Usuario usuario = ConversorUsuario.converterParaModelo(dto);

        usuario.setId(id);
        usuario.setDataInativacao(usuarioBanco.getDataInativacao());
        usuario.setDataCadastro(usuarioBanco.getDataCadastro());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));

        return ConversorUsuario.converterParaDto(usuarioRepository.save(usuario));
    }

    @Override
    public void delete(Long id) {
        Optional<Usuario> optUsuario = usuarioRepository.findById(id);

        if (optUsuario.isEmpty()) throw new ResourceNotFoundException("Não foi possível encontrar com esse id");

        Usuario usuario = optUsuario.get();
        usuario.setDataInativacao(new Date());

        usuarioRepository.save(usuario);
    }

    private void validarUsuario(UsuarioRequestDto dto) {
        if (dto.getEmail() == null || dto.getSenha() == null)
            throw new ResourceBadRequestException("E-mail e senha são obrigatórios");
    }
}