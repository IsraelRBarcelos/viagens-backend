package br.com.aeviagens.backend.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class DadosDoCartao {
    private Long numeroDoCartao;
    private String dataValidade;
    private int cvv;
    private String nomeTitular;
    @Enumerated(EnumType.STRING)
    private Bandeira bandeira;
}
