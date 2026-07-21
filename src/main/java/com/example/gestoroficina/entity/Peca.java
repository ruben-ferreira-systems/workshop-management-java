package com.example.gestoroficina.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Table(name = "pecas")
@Getter @Setter
public class Peca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_peca")
    private Integer id;

    @Column(name = "referencia", nullable = false, unique = true, length = 50)
    private String referencia;

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @Column(name = "stock_atual", nullable = false)
    private Integer stockAtual = 0;
}