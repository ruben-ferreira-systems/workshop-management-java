package com.example.gestoroficina.repository;

import com.example.gestoroficina.entity.Peca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PecaRepository extends JpaRepository<Peca, Integer> {
    List<Peca> findByNomeContainingIgnoreCaseOrReferenciaContainingIgnoreCase(String nome, String referencia);
    List<Peca> findByStockAtualEquals(Integer stock);
}