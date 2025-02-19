package com.example.api_sistemafinanceiro.gui.domain.repository;

import com.example.api_sistemafinanceiro.gui.domain.model.Titulo;
import com.example.api_sistemafinanceiro.gui.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TituloRepository extends JpaRepository<Titulo, Long> {

    @Query(value = "SELECT * FROM public.titulo " +
            "WHERE data_vencimento BETWEEN :periodoInicial AND :periodoFinal",
            nativeQuery = true)
    List<Titulo> obterFluxoCaixaPorDataVencimento(
            @Param("periodoInicial") LocalDateTime periodoInicial,
            @Param("periodoFinal") LocalDateTime periodoFinal
    );

    List<Titulo> findByUsuario(Usuario usuario);
}
