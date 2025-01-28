package com.example.api_sistemafinanceiro.gui.domain.service;

import com.example.api_sistemafinanceiro.gui.domain.exception.ResourceBadRequestException;
import com.example.api_sistemafinanceiro.gui.domain.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ICrudService<Req, Res> {
    List<Res> findAll() throws ResourceNotFoundException;
    Res findById(Long id) throws ResourceNotFoundException;
    Res create(Req dto) throws ResourceBadRequestException;
    Res update(Long id, Req dto) throws ResourceBadRequestException;
    void delete(Long id) throws ResourceBadRequestException;
}
