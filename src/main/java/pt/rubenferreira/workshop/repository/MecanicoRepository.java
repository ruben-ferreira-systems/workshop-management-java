package pt.rubenferreira.workshop.repository;

import pt.rubenferreira.workshop.entity.Mecanico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MecanicoRepository extends JpaRepository<Mecanico, Integer> {
    Optional<Mecanico> findByNif(String nif);
    List<Mecanico> findByNomeContainingIgnoreCase(String nome);
    List<Mecanico> findByNifContaining(String nif);
    List<Mecanico> findByEspecialidadeContainingIgnoreCase(String especialidade);
}
