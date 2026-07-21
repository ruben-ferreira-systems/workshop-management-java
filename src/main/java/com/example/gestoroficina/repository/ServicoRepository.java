package com.example.gestoroficina.repository;

import com.example.gestoroficina.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer> {
    List<Servico> findByDesignacaoContainingIgnoreCase(String designacao);
}