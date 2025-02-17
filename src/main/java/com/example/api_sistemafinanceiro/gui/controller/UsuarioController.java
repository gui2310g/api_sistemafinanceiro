package com.example.api_sistemafinanceiro.gui.controller;

import com.example.api_sistemafinanceiro.gui.domain.service.UsuarioService;
import com.example.api_sistemafinanceiro.gui.dto.Usuario.UsuarioRequestDto;
import com.example.api_sistemafinanceiro.gui.dto.Usuario.UsuarioResponseDto;
import com.example.api_sistemafinanceiro.gui.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> findAll(){
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> create(@RequestBody UsuarioRequestDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.create(dto));
    }

    @PutMapping
    public ResponseEntity<UsuarioResponseDto> update(Authentication auth, @RequestBody UsuarioRequestDto dto) {
        return ResponseEntity.ok(usuarioService.update(jwtUtil.getAuthenticatedUserId(auth), dto));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(Authentication authentication){
        usuarioService.delete(jwtUtil.getAuthenticatedUserId(authentication));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}