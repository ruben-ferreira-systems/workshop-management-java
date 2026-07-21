package com.example.gestoroficina.repository;

import com.example.gestoroficina.entity.LinhaPeca;
import com.example.gestoroficina.entity.LinhaPecaPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinhaPecaRepository extends JpaRepository<LinhaPeca, LinhaPecaPK> {}