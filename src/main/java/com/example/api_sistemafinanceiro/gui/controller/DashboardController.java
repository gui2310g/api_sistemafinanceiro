package com.example.api_sistemafinanceiro.gui.controller;

import com.example.api_sistemafinanceiro.gui.domain.service.DashboardService;
import com.example.api_sistemafinanceiro.gui.dto.Dashboard.DashboardResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
    

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<DashboardResponseDto> obterFluxodeCaixa(
            @RequestParam(name = "periodoInicial")
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime periodoInicial,

            @RequestParam(name = "periodoFinal")
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime periodoFinal) {

        return ResponseEntity.ok(dashboardService.obterFluxodeCaixa(periodoInicial, periodoFinal));
    }

}
