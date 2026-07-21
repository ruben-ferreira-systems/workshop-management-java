package com.example.gestoroficina.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "clientes")
@Getter @Setter
public class Cliente {

    @Id
    @Column(name = "id_cliente")
    private Integer id;

    @Column(name = "nif")
    private String nif;

    @Column(name = "nome")
    private String nome;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "morada")
    private String morada;
}