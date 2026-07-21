package pt.rubenferreira.workshop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "folhas_obra")
@Getter @Setter @NoArgsConstructor
public class FolhaObra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_folha")
    private Integer id;

    @Column(name = "data_entrada")
    private LocalDate dataEntrada;

    @Column(name = "descricao", length = 100)
    private String descricao;

    @Column(name = "descricao_detalhe")
    private String descricaoDetalhe;

    @Column(name = "observacoes")
    private String observacoes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_veiculo")
    private Veiculo veiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mecanico")
    private Mecanico mecanico;

    @OneToMany(mappedBy = "folhaObra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LinhaServico> linhasServico = new ArrayList<>();

    @OneToMany(mappedBy = "folhaObra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LinhaPeca> linhasPeca = new ArrayList<>();
}
