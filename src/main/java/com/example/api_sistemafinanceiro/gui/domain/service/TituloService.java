package com.example.api_sistemafinanceiro.gui.domain.service;

import com.example.api_sistemafinanceiro.gui.domain.exception.ResourceBadRequestException;
import com.example.api_sistemafinanceiro.gui.domain.exception.ResourceNotFoundException;
import com.example.api_sistemafinanceiro.gui.domain.model.CentroDeCusto;
import com.example.api_sistemafinanceiro.gui.domain.model.Titulo;
import com.example.api_sistemafinanceiro.gui.domain.model.Usuario;
import com.example.api_sistemafinanceiro.gui.domain.repository.TituloRepository;
import com.example.api_sistemafinanceiro.gui.dto.CentroDeCusto.CentrodeCustoResponseDto;
import com.example.api_sistemafinanceiro.gui.dto.Titulo.TituloRequestDto;
import com.example.api_sistemafinanceiro.gui.dto.Titulo.TituloResponseDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TituloService implements ICrudService<TituloRequestDto, TituloResponseDto> {

    private final TituloRepository tituloRepository;

    private ModelMapper mapper;

    @Override
    public List<TituloResponseDto> findAll() {
        return tituloRepository.findAll().stream()
                .map(titulo -> mapper.map(titulo, TituloResponseDto.class)).toList();
    }

    @Override
    public TituloResponseDto findById(Long id) {
        return tituloRepository.findById(id).map(titulo -> mapper.map(titulo, TituloResponseDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("Nao foi achado um titulo com esse id " + id));
    }

    @Override
    public TituloResponseDto create(TituloRequestDto dto) {
        ValidarTitulo(dto);

        Titulo titulo = mapper.map(dto, Titulo.class);

        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        titulo.setUsuario(usuario);
        titulo.setId(null);

        return mapper.map(tituloRepository.save(titulo), TituloResponseDto.class);
    }

    @Override
    public TituloResponseDto update(Long id, TituloRequestDto dto) {
        findById(id);
        ValidarTitulo(dto);

        Titulo titulo = mapper.map(dto, Titulo.class);
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        titulo.setUsuario(usuario);
        titulo.setId(id);

        return mapper.map(tituloRepository.save(titulo), TituloResponseDto.class);
    }

    @Override
    public void delete(Long id) {
        findById(id);
        tituloRepository.deleteById(id);
    }

    private void ValidarTitulo(TituloRequestDto dto) {
        if(dto.getDataVencimento() == null || dto.getValor() == null
                || dto.getDescricao() == null || dto.getTipo() == null) {
            throw new ResourceBadRequestException("os campos tipo, vencimento, descrição e valor são obrigatorios");
        }
    }
}
