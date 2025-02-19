package com.example.api_sistemafinanceiro.gui.domain.service;

import com.example.api_sistemafinanceiro.gui.domain.enums.ETipoTitulo;
import com.example.api_sistemafinanceiro.gui.dto.Dashboard.DashboardResponseDto;
import com.example.api_sistemafinanceiro.gui.dto.Titulo.TituloResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DashboardService {

    private TituloService tituloService;

    public DashboardResponseDto obterFluxodeCaixa(LocalDateTime periodoInicial, LocalDateTime periodoFinal) {
        List<TituloResponseDto> titulos = tituloService.obterPorDataVencimento(periodoInicial, periodoFinal);

        List<TituloResponseDto> titulosApagar = titulos.stream()
                .filter(titulo -> titulo.getTipo() == ETipoTitulo.APAGAR).toList();

        double totalApagar = titulosApagar.stream().mapToDouble(TituloResponseDto::getValor).sum();

        List<TituloResponseDto> titulosReceber = titulos.stream()
                .filter(titulo -> titulo.getTipo() == ETipoTitulo.ARECEBER).toList();

        double totalReceber = titulosReceber.stream().mapToDouble(TituloResponseDto::getValor).sum();

        double saldo = totalReceber - totalApagar;

        return new DashboardResponseDto(totalApagar, totalReceber, saldo, titulosApagar, titulosReceber);
    }
}
