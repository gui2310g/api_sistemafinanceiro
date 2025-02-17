package com.example.api_sistemafinanceiro.gui.dto.Dashboard;

import com.example.api_sistemafinanceiro.gui.dto.Titulo.TituloResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DashboardResponseDto {
    private Double totalApagar;

    private Double totalReceber;

    private Double saldo;

    private List<TituloResponseDto> titulosaApagar;
    private List<TituloResponseDto> titulosAReceber;

}
