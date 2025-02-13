package com.example.api_sistemafinanceiro.gui.controller;

import com.example.api_sistemafinanceiro.gui.domain.service.CentroDeCustoService;

import com.example.api_sistemafinanceiro.gui.dto.CentroDeCusto.CentrodeCustoRequestDto;
import com.example.api_sistemafinanceiro.gui.dto.CentroDeCusto.CentrodeCustoResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("centrodecusto")
public class CentrodeCustoController {
    @Autowired
    private CentroDeCustoService centroDeCustoService;

    @GetMapping
    public ResponseEntity<List<CentrodeCustoResponseDto>> findAll() {
        return ResponseEntity.ok(centroDeCustoService.findAll());
    }

    @PostMapping
    public ResponseEntity<CentrodeCustoResponseDto> create(@RequestBody CentrodeCustoRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(centroDeCustoService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CentrodeCustoResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(centroDeCustoService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CentrodeCustoResponseDto> update(
            @PathVariable Long id,
            @RequestBody CentrodeCustoRequestDto dto
    ) {
        return ResponseEntity.ok(centroDeCustoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        centroDeCustoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
