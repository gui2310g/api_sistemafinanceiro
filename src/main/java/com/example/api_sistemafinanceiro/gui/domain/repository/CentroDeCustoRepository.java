package com.example.api_sistemafinanceiro.gui.domain.repository;

import com.example.api_sistemafinanceiro.gui.domain.model.CentroDeCusto;
import com.example.api_sistemafinanceiro.gui.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CentroDeCustoRepository extends JpaRepository<CentroDeCusto, Long> {
    List<CentroDeCusto> findByUsuario(Usuario usuario);
}
