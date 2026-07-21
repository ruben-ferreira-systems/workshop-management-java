package pt.rubenferreira.workshop.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mecanicos")
@Getter @Setter
public class Mecanico {

    @Id
    @Column(name = "id_mecanico")
    private Integer id;

    @Column(name = "nif")
    private String nif;

    @Column(name = "nome")
    private String nome;

    @Column(name = "especialidade")
    private String especialidade;
}
