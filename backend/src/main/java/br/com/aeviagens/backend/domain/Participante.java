package br.com.aeviagens.backend.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Participante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true)
    private String cpf;
    @Embedded
    private DadosDoCartao dadosDoCartao;
    @Embedded
    private DadosDeLocalizacao dadosDeLocalizacao;
}
