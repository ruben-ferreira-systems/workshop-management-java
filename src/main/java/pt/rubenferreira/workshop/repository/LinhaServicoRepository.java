package pt.rubenferreira.workshop.repository;

import pt.rubenferreira.workshop.entity.LinhaServico;
import pt.rubenferreira.workshop.entity.LinhaServicoPK;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LinhaServicoRepository extends JpaRepository<LinhaServico, LinhaServicoPK> {}