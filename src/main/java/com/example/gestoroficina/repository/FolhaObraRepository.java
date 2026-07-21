package com.example.gestoroficina.repository;

import com.example.gestoroficina.entity.FolhaObra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FolhaObraRepository extends JpaRepository<FolhaObra, Integer> {
    List<FolhaObra> findByVeiculoId(Integer idVeiculo);
    List<FolhaObra> findByMecanicoId(Integer idMecanico);
    List<FolhaObra> findByDataEntradaBetween(LocalDate inicio, LocalDate fim);
    List<FolhaObra> findTop5ByOrderByDataEntradaDesc();
}