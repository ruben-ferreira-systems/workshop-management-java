package com.example.gestoroficina.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter @Setter @NoArgsConstructor
@EqualsAndHashCode
public class LinhaPecaPK implements Serializable {
    @Column(name = "id_folha") private Integer idFolha;
    @Column(name = "id_peca")  private Integer idPeca;
}