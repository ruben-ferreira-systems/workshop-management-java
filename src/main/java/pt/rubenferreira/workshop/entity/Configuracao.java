package pt.rubenferreira.workshop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "configuracoes")
@Getter @Setter
public class Configuracao {

    @Id
    @Column(name = "chave")
    private String chave;

    @Column(name = "valor", nullable = false, columnDefinition = "TEXT")
    private String valor;
}