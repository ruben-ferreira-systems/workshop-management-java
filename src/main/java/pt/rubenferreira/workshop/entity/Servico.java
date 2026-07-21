package pt.rubenferreira.workshop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Table(name = "servicos")
@Getter @Setter
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servico")
    private Integer id;

    @Column(name = "designacao", nullable = false, length = 150)
    private String designacao;

    @Column(name = "preco_tabela", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoTabela;
}