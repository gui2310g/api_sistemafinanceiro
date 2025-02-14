package com.example.api_sistemafinanceiro.gui.controller;

import com.example.api_sistemafinanceiro.gui.domain.service.TituloService;
import com.example.api_sistemafinanceiro.gui.dto.Titulo.TituloRequestDto;
import com.example.api_sistemafinanceiro.gui.dto.Titulo.TituloResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/titulos")
public class TituloController {

    @Autowired
    private TituloService tituloService;

    @GetMapping
    public ResponseEntity<List<TituloResponseDto>> findAll(){
        return ResponseEntity.ok(tituloService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TituloResponseDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(tituloService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TituloResponseDto> create(@RequestBody TituloRequestDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(tituloService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TituloResponseDto> update(@PathVariable Long id, @RequestBody TituloRequestDto dto){
        return ResponseEntity.ok(tituloService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        tituloService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
