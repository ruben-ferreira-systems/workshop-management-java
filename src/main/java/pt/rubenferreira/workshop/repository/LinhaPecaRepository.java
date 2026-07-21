package pt.rubenferreira.workshop.repository;

import pt.rubenferreira.workshop.entity.LinhaPeca;
import pt.rubenferreira.workshop.entity.LinhaPecaPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinhaPecaRepository extends JpaRepository<LinhaPeca, LinhaPecaPK> {}