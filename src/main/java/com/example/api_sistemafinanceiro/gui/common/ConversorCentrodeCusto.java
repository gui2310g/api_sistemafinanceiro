package com.example.api_sistemafinanceiro.gui.common;

import com.example.api_sistemafinanceiro.gui.domain.model.CentroDeCusto;
import com.example.api_sistemafinanceiro.gui.domain.model.Usuario;
import com.example.api_sistemafinanceiro.gui.dto.CentroDeCusto.CentrodeCustoRequestDto;
import com.example.api_sistemafinanceiro.gui.dto.CentroDeCusto.CentrodeCustoResponseDto;
import com.example.api_sistemafinanceiro.gui.dto.Usuario.UsuarioRequestDto;
import com.example.api_sistemafinanceiro.gui.dto.Usuario.UsuarioResponseDto;
import org.modelmapper.ModelMapper;

public class ConversorCentrodeCusto {

    private static final ModelMapper mapper = new ModelMapper();

    public static CentrodeCustoResponseDto converterparaDto(CentroDeCusto centroDeCusto) {
        return mapper.map(centroDeCusto, CentrodeCustoResponseDto.class);
    }

    public static CentroDeCusto converterparaModelo(CentrodeCustoRequestDto dto) {
        return mapper.map(dto, CentroDeCusto.class);
    }
}
