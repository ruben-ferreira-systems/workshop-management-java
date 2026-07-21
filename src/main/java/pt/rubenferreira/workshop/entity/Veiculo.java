package pt.rubenferreira.workshop.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "veiculos")
@Getter @Setter
public class Veiculo {

    @Id
    @Column(name = "id_veiculo")
    private Integer id;

    @Column(name = "matricula")
    private String matricula;

    @Column(name = "marca")
    private String marca;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "ano")
    private Integer ano;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
}
