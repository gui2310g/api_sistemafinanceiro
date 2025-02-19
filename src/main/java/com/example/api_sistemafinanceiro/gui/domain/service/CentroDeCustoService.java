package com.example.api_sistemafinanceiro.gui.domain.service;

import com.example.api_sistemafinanceiro.gui.common.ConversorCentrodeCusto;
import com.example.api_sistemafinanceiro.gui.domain.exception.ResourceNotFoundException;
import com.example.api_sistemafinanceiro.gui.domain.model.CentroDeCusto;
import com.example.api_sistemafinanceiro.gui.domain.model.Usuario;
import com.example.api_sistemafinanceiro.gui.domain.repository.CentroDeCustoRepository;
import com.example.api_sistemafinanceiro.gui.dto.CentroDeCusto.CentrodeCustoRequestDto;
import com.example.api_sistemafinanceiro.gui.dto.CentroDeCusto.CentrodeCustoResponseDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CentroDeCustoService implements ICrudService<CentrodeCustoRequestDto, CentrodeCustoResponseDto> {

    private final CentroDeCustoRepository centroDeCustoRepository;


    @Override
    public List<CentrodeCustoResponseDto> findAll() {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return centroDeCustoRepository.findByUsuario(usuario).stream()
                .map(ConversorCentrodeCusto::converterparaDto).toList();
    }

    @Override
    public CentrodeCustoResponseDto findById(Long id) {
        return centroDeCustoRepository.findById(id).map(ConversorCentrodeCusto::converterparaDto)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi achado um centro de custo com id " + id));
    }

    @Override
    public CentrodeCustoResponseDto create(CentrodeCustoRequestDto dto) {
        CentroDeCusto centroDeCusto = ConversorCentrodeCusto.converterparaModelo(dto);

        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        centroDeCusto.setUsuario(usuario);

        return ConversorCentrodeCusto.converterparaDto(centroDeCustoRepository.save(centroDeCusto));
    }

    @Override
    public CentrodeCustoResponseDto update(Long id, CentrodeCustoRequestDto dto) {
        findById(id);

        CentroDeCusto centroDeCusto = ConversorCentrodeCusto.converterparaModelo(dto);
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        centroDeCusto.setUsuario(usuario);
        centroDeCusto.setId(id);

        return ConversorCentrodeCusto.converterparaDto(centroDeCustoRepository.save(centroDeCusto));
    }

    @Override
    public void delete(Long id) {
        findById(id);
        centroDeCustoRepository.deleteById(id);
    }
}
