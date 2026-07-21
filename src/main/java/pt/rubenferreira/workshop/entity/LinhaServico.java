package pt.rubenferreira.workshop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "linhas_servicos")
@Getter @Setter @NoArgsConstructor
public class LinhaServico {

    @EmbeddedId
    private LinhaServicoPK id = new LinhaServicoPK();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idFolha")
    @JoinColumn(name = "id_folha")
    private FolhaObra folhaObra;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idServico")
    @JoinColumn(name = "id_servico")
    private Servico servico;

    @Column(name = "preco_aplicado")
    private BigDecimal precoAplicado;
}