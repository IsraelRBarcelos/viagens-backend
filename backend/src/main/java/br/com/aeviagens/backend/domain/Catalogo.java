package br.com.aeviagens.backend.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Catalogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    List<Viagem> viagens = new ArrayList<>();
    @OneToMany
    List<Participante> participantes = new ArrayList<>();
    @ManyToOne
    Participante host;
    String hash;
    String descricao;
    String titulo;

    @PrePersist
    public void gerarHash() {
        if (this.hash == null || this.hash.isBlank()) {
            this.hash = UUID.randomUUID().toString();
        }
    }
}
