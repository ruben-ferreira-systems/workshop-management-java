package pt.rubenferreira.workshop.repository;

import pt.rubenferreira.workshop.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer> {
    List<Servico> findByDesignacaoContainingIgnoreCase(String designacao);
}