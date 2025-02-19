package com.example.api_sistemafinanceiro.gui.common;

import com.example.api_sistemafinanceiro.gui.domain.model.Titulo;
import com.example.api_sistemafinanceiro.gui.dto.Titulo.TituloRequestDto;
import com.example.api_sistemafinanceiro.gui.dto.Titulo.TituloResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.ui.Model;

public class ConversorTitulo {

    private static final ModelMapper mapper = new ModelMapper();

    public static Titulo converterParaModelo(TituloRequestDto tituloRequestDto) {
        return mapper.map(tituloRequestDto, Titulo.class);
    }

    public static TituloResponseDto converterParaDto(Titulo titulo) {
        return mapper.map(titulo, TituloResponseDto.class);
    }
}
