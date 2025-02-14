package com.example.api_sistemafinanceiro.gui.domain.service;

import com.example.api_sistemafinanceiro.gui.domain.exception.ResourceBadRequestException;
import com.example.api_sistemafinanceiro.gui.domain.exception.ResourceNotFoundException;
import com.example.api_sistemafinanceiro.gui.domain.model.Usuario;
import com.example.api_sistemafinanceiro.gui.domain.repository.UsuarioRepository;
import com.example.api_sistemafinanceiro.gui.dto.Usuario.UsuarioRequestDto;
import com.example.api_sistemafinanceiro.gui.dto.Usuario.UsuarioResponseDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UsuarioService implements ICrudService<UsuarioRequestDto, UsuarioResponseDto> {

    private UsuarioRepository usuarioRepository;

    private ModelMapper mapper;

    private PasswordEncoder passwordEncoder;

    @Override
    public List<UsuarioResponseDto> findAll() {
        return usuarioRepository.findAll().stream()
                .map(usuario -> mapper.map(usuario, UsuarioResponseDto.class)).toList();
    }

    @Override
    public UsuarioResponseDto findById(Long id) {
        return usuarioRepository.findById(id)
                .map(usuario -> mapper.map(usuario, UsuarioResponseDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("Não foi achado usuario com esse id" + id));
    }

    public UsuarioResponseDto findByEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .map(usuario -> mapper.map(usuario, UsuarioResponseDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("Não foi achado usuario com esse email"));
    }

    @Override
    public UsuarioResponseDto create(UsuarioRequestDto dto) {
       validarUsuario(dto);

       if(usuarioRepository.findByEmail(dto.getEmail()).isPresent())
           throw new ResourceBadRequestException("Email ja existente");

       Usuario usuario = mapper.map(dto, Usuario.class);
       usuario.setDataCadastro(new Date());
       usuario.setSenha(passwordEncoder.encode(dto.getSenha()));

       return mapper.map(usuarioRepository.save(usuario), UsuarioResponseDto.class);
    }

    @Override
    public UsuarioResponseDto update(Long id, UsuarioRequestDto dto) {
        UsuarioResponseDto usuarioBanco = findById(id);
        validarUsuario(dto);

        Usuario usuario = mapper.map(dto, Usuario.class);

        usuario.setId(id);
        usuario.setDataInativacao(usuarioBanco.getDataInativacao());
        usuario.setDataCadastro(usuarioBanco.getDataCadastro());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));

        return mapper.map(usuarioRepository.save(usuario), UsuarioResponseDto.class);
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