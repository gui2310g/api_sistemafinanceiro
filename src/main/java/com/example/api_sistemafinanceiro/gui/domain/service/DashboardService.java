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
        Double totalApagar = 0.0;
        Double totalReceber = 0.0;

        List<TituloResponseDto> titulosApagar = new ArrayList<>();
        List<TituloResponseDto> titulosReceber = new ArrayList<>();
        Double saldo = 0.0;

        for(TituloResponseDto titulo : titulos) {
            if (titulo.getTipo() == ETipoTitulo.APAGAR) {
                totalApagar += titulo.getValor();
                titulosApagar.add(titulo);
            } else {
                totalReceber += titulo.getValor();
                titulosReceber.add(titulo);
            }
        }
        saldo = totalReceber - totalApagar;
        
        return new DashboardResponseDto(totalApagar, totalReceber, saldo, titulosApagar, titulosReceber);
    }
}
