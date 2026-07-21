package com.example.gestoroficina.repository;

import com.example.gestoroficina.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> {
    Optional<Veiculo> findByMatricula(String matricula);
    List<Veiculo> findByMatriculaContainingIgnoreCase(String matricula);
    List<Veiculo> findByMarcaContainingIgnoreCase(String marca);
    List<Veiculo> findByClienteNomeContainingIgnoreCase(String nome);
}
