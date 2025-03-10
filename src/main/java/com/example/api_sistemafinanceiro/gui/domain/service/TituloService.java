package com.example.api_sistemafinanceiro.gui.domain.service;

import com.example.api_sistemafinanceiro.gui.common.ConversorTitulo;
import com.example.api_sistemafinanceiro.gui.domain.exception.ResourceBadRequestException;
import com.example.api_sistemafinanceiro.gui.domain.exception.ResourceNotFoundException;
import com.example.api_sistemafinanceiro.gui.domain.model.Titulo;
import com.example.api_sistemafinanceiro.gui.domain.model.Usuario;
import com.example.api_sistemafinanceiro.gui.domain.repository.TituloRepository;
import com.example.api_sistemafinanceiro.gui.dto.Titulo.TituloRequestDto;
import com.example.api_sistemafinanceiro.gui.dto.Titulo.TituloResponseDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class TituloService implements ICrudService<TituloRequestDto, TituloResponseDto> {

    private final TituloRepository tituloRepository;

    private ModelMapper mapper;

    @Override
    public List<TituloResponseDto> findAll() {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return tituloRepository.findByUsuario(usuario).stream().map(ConversorTitulo::converterParaDto).toList();
    }

    @Override
    public TituloResponseDto findById(Long id) {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return tituloRepository.findById(id)
                .filter(titulo -> titulo.getUsuario().getId().equals(usuario.getId()))
                .map(ConversorTitulo::converterParaDto)
                .orElseThrow(() -> new ResourceNotFoundException("Nao foi achado um titulo com esse id " + id));
    }

    @Override
    public TituloResponseDto create(TituloRequestDto dto) {
        ValidarTitulo(dto);

        Titulo titulo = ConversorTitulo.converterParaModelo(dto);

        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        titulo.setUsuario(usuario);
        titulo.setDataCadastro(new Date());
        titulo.setId(null);

        return ConversorTitulo.converterParaDto(tituloRepository.save(titulo));
    }

    @Override
    public TituloResponseDto update(Long id, TituloRequestDto dto) {
        findById(id);
        ValidarTitulo(dto);

        Titulo titulo = ConversorTitulo.converterParaModelo(dto);
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        titulo.setUsuario(usuario);
        titulo.setId(id);

        return ConversorTitulo.converterParaDto(tituloRepository.save(titulo));
    }

    @Override
    public void delete(Long id) {
        findById(id);
        tituloRepository.deleteById(id);
    }

    public List<TituloResponseDto> obterPorDataVencimento(LocalDateTime periodoInicial, LocalDateTime periodoFinal) {
        return tituloRepository.obterFluxoCaixaPorDataVencimento(periodoInicial, periodoFinal).stream()
                .map(ConversorTitulo::converterParaDto).toList();
    }


    private void ValidarTitulo(TituloRequestDto dto) {
        if(dto.getDataVencimento() == null || dto.getValor() == null
                || dto.getDescricao() == null || dto.getTipo() == null) {
            throw new ResourceBadRequestException("os campos tipo, vencimento, descrição e valor são obrigatorios");
        }
    }
}
