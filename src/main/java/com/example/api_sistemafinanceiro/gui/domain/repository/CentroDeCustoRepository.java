package com.example.api_sistemafinanceiro.gui.domain.repository;

import com.example.api_sistemafinanceiro.gui.domain.model.CentroDeCusto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentroDeCustoRepository extends JpaRepository<CentroDeCusto, Long> {
}
