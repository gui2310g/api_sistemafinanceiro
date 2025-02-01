package com.example.api_sistemafinanceiro.gui.domain.service;

import com.example.api_sistemafinanceiro.gui.domain.exception.ResourceBadRequestException;
import com.example.api_sistemafinanceiro.gui.domain.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ICrudService<Req, Res> {
    List<Res> findAll();
    Res findById(Long id);
    Res create(Req dto);
    Res update(Long id, Req dto);
    void delete(Long id);
}
