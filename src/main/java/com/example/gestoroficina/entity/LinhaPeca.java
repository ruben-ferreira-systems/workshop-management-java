package com.example.gestoroficina.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "linhas_pecas")
@Getter @Setter @NoArgsConstructor
public class LinhaPeca {

    @EmbeddedId
    private LinhaPecaPK id = new LinhaPecaPK();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idFolha")
    @JoinColumn(name = "id_folha")
    private FolhaObra folhaObra;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idPeca")
    @JoinColumn(name = "id_peca")
    private Peca peca;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "preco_venda_hist")
    private BigDecimal precoVendaHist;
}